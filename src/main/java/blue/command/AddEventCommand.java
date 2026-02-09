package blue.command;

import blue.exceptions.BlueException;
import blue.storage.Storage;
import blue.task.Event;
import blue.task.Task;
import blue.task.TaskList;
import blue.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
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
            throw new BlueException("The description can't be empty! =/");
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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        BlueException emptyException = new BlueException("The description can't be empty! =/");

        String[] eventInfo = new String[3];
        String[] fromSplitArr = inputArgs.split("/from", 2);
        eventInfo[0] = fromSplitArr[0].trim();

        if (eventInfo[0].isEmpty()) {
            throw emptyException;
        }

        BlueException eventException = new BlueException("Events must have start and end times!!! <(˶`ロ´˶)> ");

        if (fromSplitArr.length < 2) {
            throw eventException;
        }
        String eventRemainder = fromSplitArr[1].trim();
        if (eventRemainder.isEmpty()) {
            throw eventException;
        }
        String[] toSplitArr = eventRemainder.split("/to", 2);
        eventInfo[1] = toSplitArr[0].trim();
        if (toSplitArr.length < 2) {
            throw eventException;
        }
        eventInfo[2] = toSplitArr[1].trim();
        if (eventInfo[2].isEmpty()) {
            throw eventException;
        }

        try {
            Task task = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
            taskList.add(task);
            ui.addTaskMessage(task, taskList.size());

            storage.save(taskList);
        } catch (DateTimeParseException e) {
            throw new BlueException("Uh oh! I don't understand that date format, try yyyy-mm-dd!");
        }
    }
}
