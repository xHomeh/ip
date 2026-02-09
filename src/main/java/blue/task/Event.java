/**
 * Represents an Event task with start and end dates.
 * <p>
 * An Event task extends Task by adding start and end dates.
 * Validates that end date is not before start date during construction. Uses the same
 * dual-format date handling as Deadline for storage and display.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
package blue.task;

import blue.exceptions.BlueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /** Formatter for compact storage: yyyy M d (e.g., "2026 1 1") */
    private static final DateTimeFormatter FORMAT_STORAGE = DateTimeFormatter.ofPattern("yyyy M d");

    /** Formatter for display: dd MMM yyyy (e.g., "1 Jan 2026") */
    private static final DateTimeFormatter FORMAT_DISPLAY = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructs a new Event task with description and date range.
     *
     * @param description The event description.
     * @param from        Start date string in storage format (yyyy M d).
     * @param to          End date string in storage format (yyyy M d).
     * @throws BlueException if end date is before start date.
     */
    public Event(String description, String from, String to) throws BlueException {
        super(description);
        this.from = LocalDate.parse(from, FORMAT_STORAGE);
        this.to = LocalDate.parse(to, FORMAT_STORAGE);

        if (this.from.isAfter(this.to)) {
            throw new BlueException("Events must end after they start!!!");
        }
    }

    /**
     * Returns the storage format for an Event task.
     *
     * @return Pipe-delimited string with type "E" and date range for persistence.
     */
    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + " | "
                + from.format(FORMAT_STORAGE) + " | "
                + to.format(FORMAT_STORAGE);
    }

    /**
     * Returns the display representation of an Event task.
     *
     * @return User-friendly string showing type, status, description, and formatted date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)",
                from.format(FORMAT_DISPLAY),
                to.format(FORMAT_DISPLAY));
    }
}
