import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        ui.printList(taskList.getTasks());
    }
}
