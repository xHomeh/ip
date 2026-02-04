package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param args Single integer representing 1-based task index.
     * @throws BlueException if argument is not a positive integer.
     */
    public DeleteCommand(String args) throws BlueException {
        int idx;
        try {
            idx = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new BlueException("Give me a number ( ｡ •̀ ᴖ •́ ｡)");
        }
        if (idx <= 0) {
            throw new BlueException("Number must be positive!!! ୧(๑•̀ᗝ•́)૭");
        }
        this.idx = idx;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        if (idx > taskList.size()) {
            throw new BlueException("There isn't a task " + idx + "!  (•̀⤙•́ )");
        }
        Task task = taskList.get(idx - 1);
        taskList.remove(idx - 1);
        ui.deleteTaskMessage(task);

        storage.save(taskList);
    }
}
