package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Deadline;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    String args;

    /**
     * Constructs an AddDeadlineCommand with raw input arguments.
     *
     * @param args Input string in format "description /by dueDate".
     * @throws BlueException if input is completely empty.
     */
    public AddDeadlineCommand(String args) throws BlueException {
        if (args.isEmpty()) {
            throw new BlueException("The description can't be empty! =/");
        }
        this.args = args;
    }

    /**
     * Parses deadline input and adds Deadline task.
     * <p>
     * Expected format: "description /by yyyy M d". Splits on "/by", validates
     * both description and due date are non-empty, creates Deadline,
     * adds to list, shows success message, and saves to storage.
     * </p>
     *
     * @param taskList Current list of tasks to modify.
     * @param ui       User interface for displaying success message.
     * @param storage  Storage handler for persisting changes.
     * @throws BlueException if missing description, missing "/by" clause, empty due date,
     *                       or invalid date format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        BlueException emptyException = new BlueException("The description can't be empty! =/");

        String[] deadlineInfo = new String[2];
        String[] by = args.split("/by", 2);

        deadlineInfo[0] = by[0].trim();

        if (deadlineInfo[0].isEmpty()) {
            throw emptyException;
        }

        BlueException deadlineException = new BlueException("Deadlines must have a deadline... (ꐦ¬_¬)");

        if (by.length < 2) {
            throw deadlineException;
        }

        deadlineInfo[1] = by[1].trim();
        if (deadlineInfo[1].isEmpty()) {
            throw deadlineException;
        }

        try {
            Task task = new Deadline(deadlineInfo[0], deadlineInfo[1]);
            taskList.add(task);
            ui.addTaskMessage(task, taskList.size());

            storage.save(taskList);
        } catch (DateTimeParseException e) {
            throw new BlueException("Uh oh! I don't understand that date format, try yyyy-mm-dd!");
        }
    }
}
