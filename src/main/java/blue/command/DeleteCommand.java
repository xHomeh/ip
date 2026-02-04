package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

public class DeleteCommand extends Command {
    private final int idx;

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
