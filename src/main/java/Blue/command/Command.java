package Blue.command;

import Blue.exceptions.BlueException;
import Blue.storage.Storage;
import Blue.task.TaskList;
import Blue.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException;

    public boolean isExit() {
        return false;
    }
}