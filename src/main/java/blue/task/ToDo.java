package blue.task;

/**
 * Represents a simple ToDo task without any time constraints.
 * <p>
 * A ToDo is the most basic task type that only requires a description.
 * It extends Task and adds type-specific formatting for storage
 * and display purposes.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the storage format for a ToDo task.
     *
     * @return Pipe-delimited string prefixed with "T" for file persistence.
     */
    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }

    /**
     * Returns the display representation of a ToDo task.
     *
     * @return User-friendly string with ToDo type indicator.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
