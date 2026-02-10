package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Base class for all command implementations in the Blue task management system.
 * <p>
 * Defines the contract for command execution and exit status checking.
 * All concrete commands extend this abstract class and implement the
 * execute(TaskList, Ui, Storage) method to perform their specific operations.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
public abstract class Command {

    /**
     * Executes the command's specific operation.
     *
     * @param taskList Current list of tasks to operate on.
     * @param ui       User interface for displaying results/messages.
     * @param storage  File storage for saving task changes.
     * @throws BlueException if command execution fails due to invalid input, bounds errors, etc.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException;

    /**
     * Checks if this command terminates the application.
     * <p>
     * Most commands return false. Exit commands override to return true.
     * </p>
     *
     * @return true if this command should exit the application, false otherwise.
     */
    public boolean shouldExit() {
        return false;
    }
}
