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
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param inputArgs Single integer representing 1-based task index.
     * @throws BlueException if argument is not a positive integer.
     */
    public UnmarkCommand(String inputArgs) throws BlueException {
        int unmarkIdx;
        try {
            unmarkIdx = Integer.parseInt(inputArgs);
        } catch (NumberFormatException e) {
            throw new BlueException("Give me a number ( ｡ •̀ ᴖ •́ ｡)");
        }
        if (unmarkIdx <= 0) {
            throw new BlueException("Number must be positive!!! ୧(๑•̀ᗝ•́)૭");
        }
        this.taskIndex = unmarkIdx;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        if (taskIndex > taskList.size()) {
            throw new BlueException("There isn't a task " + taskIndex + "!  (•̀⤙•́ )");
        }
        Task task = taskList.get(taskIndex - 1);
        task.unmarkDone();

        ui.taskUnmarkMessage(task);

        storage.save(taskList);
    }
}
