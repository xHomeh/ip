package Blue.task;

import Blue.exceptions.BlueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    private static final DateTimeFormatter FORMAT_STORAGE = DateTimeFormatter.ofPattern("yyyy M d");
    private static final DateTimeFormatter FORMAT_DISPLAY = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Event(String description, String from, String to) throws BlueException {
        super(description);
        this.from = LocalDate.parse(from, FORMAT_STORAGE);
        this.to = LocalDate.parse(to, FORMAT_STORAGE);

        if (this.from.isAfter(this.to)) {
            throw new BlueException("Events must end after they start!!!");
        }
    }

    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + " | " + from.format(FORMAT_STORAGE) + " | " + to.format(FORMAT_STORAGE);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", from.format(FORMAT_DISPLAY), to.format(FORMAT_DISPLAY));
    }
}
