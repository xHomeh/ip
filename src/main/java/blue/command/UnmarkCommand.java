package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Represents a command that unmarks a task as done in task list.
 */
public class UnmarkCommand extends Command {
    private boolean shouldUnmarkAll = false;
    private int[] taskIndices;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param inputArgs Single integer representing 1-based task index.
     * @throws BlueException if argument is not a positive integer.
     */
    public UnmarkCommand(String inputArgs) throws BlueException {
        if (inputArgs.trim().equalsIgnoreCase("all")) {
            this.shouldUnmarkAll = true;
            return;
        }

        String[] argsArr = inputArgs.trim().split(",", -1);
        int[] taskIndices = new int[argsArr.length];

        for (int i = 0; i < argsArr.length; i++) {
            int idx;
            try {
                idx = Integer.parseInt(argsArr[i].trim());
            } catch (NumberFormatException e) {
                throw new BlueException("Give me a number ( ｡ •̀ ᴖ •́ ｡)");
            }
            if (idx <= 0) {
                throw new BlueException("Number must be positive!!! ୧(๑•̀ᗝ•́)૭");
            }
            taskIndices[i] = idx;
        }
        this.taskIndices = taskIndices;
    }

    /**
     * Unmarks the specified task as done and saves changes.
     *
     * @param taskList Current list of tasks to modify.
     * @param ui       User interface for displaying success message.
     * @param storage  Storage handler for persisting changes.
     * @throws BlueException if task index exceeds list bounds.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        if (shouldUnmarkAll) {
            taskList.unmarkAll();
            storage.save(taskList);
            return ui.unmarkAllTasks();
        }

        for (int i = 0; i < taskIndices.length; i++) {
            int taskIndex = taskIndices[i];
            if (taskIndex > taskList.size()) {
                throw new BlueException("There isn't a task " + taskIndex + "!  (•̀⤙•́ )");
            }
            taskIndices[i] = taskIndex - 1;
        }

        Task[] tasks = taskList.get(taskIndices);
        taskList.unmark(taskIndices);
        storage.save(taskList);
        return ui.taskUnmarkMessage(tasks);
    }
}
