package Blue;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException;

    public boolean isExit() {
        return false;
    }
}