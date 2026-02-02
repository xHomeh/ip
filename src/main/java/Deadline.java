public class Deadline extends Task {
    private final String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.due);
    }
}
