package blue.command;

import java.time.format.DateTimeParseException;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Deadline;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Represents a command that adds a Deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private static final String EMPTY_DESCRIPTION_MESSAGE = "The description can't be empty! =/";
    private static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Deadlines must have a deadline... (ꐦ¬_¬)";
    private static final String INVALID_DATE_FORMAT_MESSAGE =
            "Uh oh! I don't understand that date format, try yyyy-mm-dd!";
    private final String inputArgs;

    /**
     * Constructs an AddDeadlineCommand with raw input arguments.
     *
     * @param inputArgs Input string in format "description /by dueDate".
     * @throws BlueException if input is completely empty.
     */
    public AddDeadlineCommand(String inputArgs) throws BlueException {
        if (inputArgs.isEmpty()) {
            throw new BlueException(EMPTY_DESCRIPTION_MESSAGE);
        }
        this.inputArgs = inputArgs;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        String[] deadlineInfo = parseDeadlineInfo();
        try {
            Task task = new Deadline(deadlineInfo[0], deadlineInfo[1]);
            taskList.add(task);
            storage.save(taskList);
            return ui.addTaskMessage(task, taskList.size());
        } catch (DateTimeParseException e) {
            throw new BlueException("Uh oh! I don't understand that date format, try yyyy-mm-dd!");
        }
    }

    private String[] parseDeadlineInfo() throws BlueException {
        String[] by = inputArgs.split("/by", 2);
        String description = by[0].trim();
        if (description.isEmpty()) {
            throw new BlueException(EMPTY_DESCRIPTION_MESSAGE);
        }
        if (by.length < 2) {
            throw new BlueException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }

        String byDate = by[1].trim();
        if (byDate.isEmpty()) {
            throw new BlueException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }

        return new String[] {description, byDate};
    }
}
