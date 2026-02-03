package Blue.command;

import Blue.exceptions.BlueException;
import Blue.storage.Storage;
import Blue.task.TaskList;
import Blue.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        ui.printList(taskList.getTasks());
    }
}
