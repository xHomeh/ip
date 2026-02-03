package Blue.command;

import Blue.storage.Storage;
import Blue.task.TaskList;
import Blue.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
