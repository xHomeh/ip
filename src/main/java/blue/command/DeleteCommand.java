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
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param inputArgs Single integer representing 1-based task index.
     * @throws BlueException if argument is not a positive integer.
     */
    public DeleteCommand(String inputArgs) throws BlueException {
        int idx;
        try {
            idx = Integer.parseInt(inputArgs);
        } catch (NumberFormatException e) {
            throw new BlueException("Give me a number ( ｡ •̀ ᴖ •́ ｡)");
        }
        if (idx <= 0) {
            throw new BlueException("Number must be positive!!! ୧(๑•̀ᗝ•́)૭");
        }
        this.taskIndex = idx;
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
        if (taskIndex > taskList.size()) {
            throw new BlueException("There isn't a task " + taskIndex + "!  (•̀⤙•́ )");
        }
        Task task = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        storage.save(taskList);
        return ui.deleteTaskMessage(task);
    }
}
