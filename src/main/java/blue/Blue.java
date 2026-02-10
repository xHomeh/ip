package blue;

import blue.command.Command;
import blue.exceptions.BlueException;
import blue.parser.Parser;
import blue.storage.Storage;
import blue.task.TaskList;
import blue.ui.Ui;

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
     * Generates a response for the user's chat message.
     *
     * @param input Command that user input.
     * @return Corresponding response for the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            String response = c.execute(taskList, ui, storage);
            return response;
        } catch (BlueException e) {
            return ui.wrapTextWithLines(e.getMessage());
        }
    }
}
