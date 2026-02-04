package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException;

    public boolean isExit() {
        return false;
    }
}