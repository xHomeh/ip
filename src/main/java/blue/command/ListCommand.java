package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Represents a command that lists out the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays the current task list to the user.
     *
     * @param taskList Current list of tasks (passed but storage parameter unused).
     * @param ui       User interface for displaying the task list.
     * @param storage  Storage handler (unused for this read-only command).
     * @throws BlueException Not thrown by this implementation.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        return ui.printList(taskList.getTasks());
    }
}
