package Blue;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        return "T | " + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
