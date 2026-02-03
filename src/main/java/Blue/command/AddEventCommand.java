package Blue.command;

import Blue.exceptions.BlueException;
import Blue.storage.Storage;
import Blue.task.Event;
import Blue.task.Task;
import Blue.task.TaskList;
import Blue.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    String args;
    public AddEventCommand(String args) throws BlueException {
        if (args.isEmpty()) {
            throw new BlueException("The description can't be empty! =/");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        BlueException emptyException = new BlueException("The description can't be empty! =/");

        String[] eventInfo = new String[3];
        String[] fromSplitArr = args.split("/from", 2);
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
