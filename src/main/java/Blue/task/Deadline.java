package Blue.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate due;

    private static final DateTimeFormatter FORMAT_STORAGE = DateTimeFormatter.ofPattern("yyyy M d");
    private static final DateTimeFormatter FORMAT_DISPLAY = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Deadline(String description, String due) {
        super(description);
        this.due = LocalDate.parse(due, FORMAT_STORAGE);
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + due.format(FORMAT_STORAGE);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", due.format(FORMAT_DISPLAY));
    }
}
