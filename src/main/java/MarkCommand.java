public class MarkCommand extends Command {
    private final int idx;

    public MarkCommand(String args) throws BlueException {
        int markIdx;
        try {
            markIdx = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new BlueException("Give me a number ( ｡ •̀ ᴖ •́ ｡)");
        }
        if (markIdx <= 0) {
            throw new BlueException("Number must be positive!!! ୧(๑•̀ᗝ•́)૭");
        }
        this.idx = markIdx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        if (idx > taskList.size()) {
            throw new BlueException("There isn't a task " + idx + "!  (•̀⤙•́ )");
        }
        Task task = taskList.get(idx - 1);
        task.markDone();

        ui.taskMarkMessage(task);

        storage.save(taskList);
    }
}
