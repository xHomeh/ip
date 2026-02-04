/**
 * Base class for all task types in the Blue task management system.
 * <p>
 * Represents a generic task with a description and done/undone status.
 * Provides common functionality for marking tasks complete/incomplete and
 * generating display/storage representations used by subclasses.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
package blue.task;

public class Task {
    private final String description;
    private boolean isDone = false;

    /**
     * Constructs a new task with the given description.
     *
     * @param description The task description (non-null).
     */
    public Task(String description) {
        this.description = description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the storage format string for persistence.
     *
     * @return String in pipe-delimited format for file storage.
     */
    public String toStorageString() {
        return (isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Returns the user-friendly display representation of the task.
     *
     * @return Formatted string showing completion status and description.
     */
    @Override
    public String toString() {
        String checkbox = "[ ] ";
        if (isDone) {
            checkbox = "[X] ";
        }
        return checkbox + description;
    }
}
