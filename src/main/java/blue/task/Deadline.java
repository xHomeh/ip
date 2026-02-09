package blue.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a specific due date.
 * <p>
 * A Deadline task extends Task by adding a due date parsed from storage format.
 * It displays the due date in user-friendly format while storing it in compact format.
 * Uses Java's LocalDate for date handling with predefined formatters.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
public class Deadline extends Task {
    /** Formatter for compact storage: yyyy M d (e.g., "2026 1 1") */
    private static final DateTimeFormatter FORMAT_STORAGE = DateTimeFormatter.ofPattern("yyyy M d");

    /** Formatter for display: dd MMM yyyy (e.g., "1 Jan 2026") */
    private static final DateTimeFormatter FORMAT_DISPLAY = DateTimeFormatter.ofPattern("dd MMM yyyy");

    private final LocalDate due;

    /**
     * Constructs a new Deadline task with description and due date.
     *
     * @param description The task description.
     * @param due         Due date string in storage format (yyyy M d).
     */
    public Deadline(String description, String due) {
        super(description);
        this.due = LocalDate.parse(due, FORMAT_STORAGE);
    }

    /**
     * Returns the storage format for a Deadline task.
     *
     * @return Pipe-delimited string with type "D" and due date for persistence.
     */
    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | "
                + due.format(FORMAT_STORAGE);
    }

    /**
     * Returns the display representation of a Deadline task.
     *
     * @return User-friendly string showing type, status, description, and formatted due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", due.format(FORMAT_DISPLAY));
    }
}
