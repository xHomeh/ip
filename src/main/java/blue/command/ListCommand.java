package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        ui.printList(taskList.getTasks());
    }
}
