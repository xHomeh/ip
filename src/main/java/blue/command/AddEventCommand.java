package blue.command;

import java.time.format.DateTimeParseException;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Event;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

/**
 * Represents a command that adds an Event task to the task list.
 */
public class AddEventCommand extends Command {
    private static final String EMPTY_DESCRIPTION_MESSAGE = "The description can't be empty! =/";
    private static final String INVALID_EVENT_FORMAT_MESSAGE =
            "Events must have start and end times!!! <(˶`ロ´˶)> ";
    private static final String INVALID_DATE_FORMAT_MESSAGE =
            "Uh oh! I don't understand that date format, try yyyy-mm-dd!";
    private final String inputArgs;

    /**
     * Constructs an AddEventCommand with raw input arguments.
     * </p>
     *
     * @param inputArgs Input string in format "description /from startDate /to endDate".
     * @throws BlueException if input is completely empty.
     */
    public AddEventCommand(String inputArgs) throws BlueException {
        if (inputArgs.isEmpty()) {
            throw new BlueException(EMPTY_DESCRIPTION_MESSAGE);
        }
        this.inputArgs = inputArgs;
    }

    /**
     * Parses event input and adds Event task.
     * <p>
     * Expected format: "description /from yyyy M d /to yyyy M d". Splits sequentially
     * on "/from" then "/to", validates all three components (description, start, end)
     * are non-empty, creates Event (which validates chronological order),
     * adds to list, shows success message, and saves to storage.
     * </p>
     *
     * @param taskList Current list of tasks to modify.
     * @param ui       User interface for displaying success message.
     * @param storage  Storage handler for persisting changes.
     * @throws BlueException if missing description, "/from" clause, "/to" clause,
     *                       empty dates, invalid date format, or end date before start date.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        String[] eventInfo = parseEventInfo();
        try {
            Task task = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
            taskList.add(task);
            storage.save(taskList);
            return ui.addTaskMessage(task, taskList.size());
        } catch (DateTimeParseException e) {
            throw new BlueException(INVALID_DATE_FORMAT_MESSAGE);
        }
    }

    private String[] parseEventInfo() throws BlueException {
        String[] fromSplitArr = inputArgs.split("/from", 2);
        String description = fromSplitArr[0].trim();
        if (description.isEmpty()) {
            throw new BlueException(EMPTY_DESCRIPTION_MESSAGE);
        }
        if (fromSplitArr.length < 2) {
            throw new BlueException(INVALID_EVENT_FORMAT_MESSAGE);
        }

        String eventRemainder = fromSplitArr[1].trim();
        if (eventRemainder.isEmpty()) {
            throw new BlueException(INVALID_EVENT_FORMAT_MESSAGE);
        }

        String[] toSplitArr = eventRemainder.split("/to", 2);
        String fromDate = toSplitArr[0].trim();
        if (toSplitArr.length < 2) {
            throw new BlueException(INVALID_EVENT_FORMAT_MESSAGE);
        }
        String toDate = toSplitArr[1].trim();
        if (toDate.isEmpty()) {
            throw new BlueException(INVALID_EVENT_FORMAT_MESSAGE);
        }

        return new String[] {description, fromDate, toDate};
    }
}
