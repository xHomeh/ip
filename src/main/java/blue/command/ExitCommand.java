package blue.command;

import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
