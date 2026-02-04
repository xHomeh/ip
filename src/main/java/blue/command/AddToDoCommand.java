package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.task.ToDo;
import blue.ui.Ui;

public class AddToDoCommand extends Command {
    String description;

    /**
     * Constructs an AddToDoCommand with the task description.
     *
     * @param args Task description string.
     * @throws BlueException if description is empty.
     */
    public AddToDoCommand(String args) throws BlueException {
        if (args.isEmpty()) {
            throw new BlueException("The description can't be empty! =/");
        }
        this.description = args;
    }

    /**
     * Adds a new ToDo task, saves changes and shows success message.
     *
     * @param taskList Current list of tasks to modify.
     * @param ui       User interface for displaying success message.
     * @param storage  Storage handler for persisting changes.
     * @throws BlueException Not thrown by this implementation (validation done in constructor).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        Task task = new ToDo(description);
        taskList.add(task);
        ui.addTaskMessage(task, taskList.size());

        storage.save(taskList);
    }
}
