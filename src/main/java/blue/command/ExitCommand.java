package blue.command;

import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Performs no operation and exits.
     *
     * @param tasks    Current task list (unused).
     * @param ui       User interface (unused - bye message handled by main loop).
     * @param storage  Storage handler (unused - no save needed).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

    /**
     * Signals that this command terminates the application.
     *
     * @return true to indicate application should exit.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
