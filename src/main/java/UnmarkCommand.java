public class UnmarkCommand extends Command {
    private final int idx;

    public UnmarkCommand(String args) throws BlueException {
        int unmarkIdx;
        try {
            unmarkIdx = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new BlueException("Give me a number ( ｡ •̀ ᴖ •́ ｡)");
        }
        if (unmarkIdx <= 0) {
            throw new BlueException("Number must be positive!!! ୧(๑•̀ᗝ•́)૭");
        }
        this.idx = unmarkIdx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BlueException {
        if (idx > taskList.size()) {
            throw new BlueException("There isn't a task " + idx + "!  (•̀⤙•́ )");
        }
        Task task = taskList.get(idx - 1);
        task.unmarkDone();

        ui.taskUnmarkMessage(task);
    }
}
