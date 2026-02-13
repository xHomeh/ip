package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private boolean shouldDeleteAll = false;
    private int[] taskIndices;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param inputArgs Single integer representing 1-based task index.
     * @throws BlueException if argument is not a positive integer.
     */
    public DeleteCommand(String inputArgs) throws BlueException {
        if (inputArgs.trim().equalsIgnoreCase("all")) {
            this.shouldDeleteAll = true;
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
     * Deletes the specified task and saves changes.
     *
     * @param taskList Current list of tasks to modify.
     * @param ui       User interface for displaying success message.
     * @param storage  Storage handler for persisting changes.
     * @throws BlueException if task index exceeds list bounds.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        if (shouldDeleteAll) {
            taskList.removeAll();
            storage.save(taskList);
            return ui.deleteAllTasks();
        }

        for (int i = 0; i < taskIndices.length; i++) {
            int taskIndex = taskIndices[i];
            if (taskIndex > taskList.size()) {
                throw new BlueException("There isn't a task " + taskIndex + "!  (•̀⤙•́ )");
            }
            taskIndices[i] = taskIndex - 1;
        }

        Task[] tasks = taskList.get(taskIndices);
        taskList.remove(taskIndices);
        storage.save(taskList);
        return ui.deleteTaskMessage(tasks);
    }
}
