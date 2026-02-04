/**
 * Blue is a task management chatbot application that helps users organize and track their tasks.
 * <p>
 * The application supports common task operations such as adding, listing, marking as done/undone,
 * deleting tasks, and finding tasks by keyword. It persists task data to a file and provides
 * a command-line interface for user interaction.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
package blue;

import blue.command.Command;
import blue.exceptions.BlueException;
import blue.parser.Parser;
import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

public class Blue {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Initializes a new instance of the Blue chatbot.
     * <p>
     * This constructor sets up the user interface, storage handler, and task list.
     * It attempts to load existing tasks from the specified file. If loading fails,
     * it initializes an empty task list and displays an error message to the user.
     * </p>
     *
     * @param fileName The name of the file used to load and save stored tasks.
     */
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

    /**
     * Starts the main execution loop of the Blue chatbot.
     * <p>
     * This method greets the user, repeatedly reads and processes user commands,
     * then executes the corresponding actions through the parser and command system.
     * The loop continues until the user issues an exit command, after which a farewell
     * message is displayed before terminating.
     * </p>
     */
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

    /**
     * The main entry point for the Blue chatbot application.
     * <p>
     * This method initializes a new instance of Blue with a default
     * storage file and begins execution by calling run().
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Blue("Blue.txt").run();
    }
}
