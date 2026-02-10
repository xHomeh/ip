package blue.command;

import java.util.ArrayList;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Represents a command that finds tasks with specified keywords in the task list.
 */
public class FindCommand extends Command {
    private final String inputArgs;

    /**
     * Constructs a FindCommand with the search keyword.
     *
     * @param inputArgs Search keyword to find in task descriptions.
     * @throws BlueException if keyword is empty or whitespace-only.
     */
    public FindCommand(String inputArgs) throws BlueException {
        if (inputArgs.trim().isEmpty()) {
            throw new BlueException("I don't know what you want me to find :(");
        }
        this.inputArgs = inputArgs.trim();
    }

    /**
     * Searches for and displays tasks matching the keyword.
     *
     * @param taskList Current list of tasks to search.
     * @param ui       User interface for displaying found tasks.
     * @param storage  Storage handler (unused for this read-only command).
     * @throws BlueException if task list search cannot find any match.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        ArrayList<Task> foundTasks = taskList.findTasks(inputArgs);
        return ui.showFoundTasks(foundTasks);
    }
}
