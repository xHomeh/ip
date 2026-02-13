package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Represents a command that marks a task as done in the task list.
 */
public class MarkCommand extends Command {
    private boolean shouldMarkAll = false;
    private int[] taskIndices;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param inputArgs Single integer representing 1-based task index.
     * @throws BlueException if argument is not a positive integer.
     */
    public MarkCommand(String inputArgs) throws BlueException {
        if (inputArgs.trim().equalsIgnoreCase("all")) {
            this.shouldMarkAll = true;
            return;
        }

        String[] argsArr = inputArgs.split(",", -1);
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
     * Marks the specified task as done and saves changes.
     *
     * @param taskList Current list of tasks to modify.
     * @param ui       User interface for displaying success message.
     * @param storage  Storage handler for persisting changes.
     * @throws BlueException if task index exceeds list bounds.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        if (shouldMarkAll) {
            taskList.markAll();
            storage.save(taskList);
            return ui.markAllTasks();
        }

        for (int i = 0; i < taskIndices.length; i++) {
            int taskIndex = taskIndices[i];
            if (taskIndex > taskList.size()) {
                throw new BlueException("There isn't a task " + taskIndex + "!  (•̀⤙•́ )");
            }
            taskIndices[i] = taskIndex - 1;
        }

        Task[] tasks = taskList.get(taskIndices);
        taskList.mark(taskIndices);
        storage.save(taskList);
        return ui.taskMarkMessage(tasks);
    }
}
