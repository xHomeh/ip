package Blue.task;

public class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toStorageString() {
        return (isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public String toString() {
        String checkbox = "[ ] ";
        if (isDone) {
            checkbox = "[X] ";
        }
        return checkbox + description;
    }
}
