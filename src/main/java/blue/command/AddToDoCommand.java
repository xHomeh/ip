package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Task;
import blue.task.TaskList;
import blue.task.ToDo;
import blue.ui.Ui;

public class AddToDoCommand extends Command {
    private final String description;
    public AddToDoCommand(String args) throws BlueException {
        if (args.isEmpty()) {
            throw new BlueException("The description can't be empty! =/");
        }
        this.description = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        Task task = new ToDo(description);
        taskList.add(task);
        ui.addTaskMessage(task, taskList.size());

        storage.save(taskList);
    }
}
