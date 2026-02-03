package Blue;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    String args;
    public AddDeadlineCommand(String args) throws BlueException {
        if (args.isEmpty()) {
            throw new BlueException("The description can't be empty! =/");
        }
        this.args = args;
    }

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
