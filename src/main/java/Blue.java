import java.util.ArrayList;
import java.util.Scanner;

import java.time.format.DateTimeParseException;

public class Blue {
    private static ArrayList<Task> taskL_Old = new ArrayList<>();
    private static final String line = "__________________________________________________ \n";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Blue(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            taskList = new TaskList(storage.load());
        } catch (BlueException e) {
            ui.showTaskLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.commandLine();
                String input = ui.readCommand();
                Command c = Parser.parseInput(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (BlueException e) {
                ui.wrapTextWithLines(e.getMessage());
            }
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Blue("Blue.txt").run();
    }
}
