package blue.ui;

import java.util.ArrayList;

import blue.exceptions.BlueException;
import blue.task.Task;


/**
 * Handles all user interface interactions for the Blue task management chatbot.
 * <p>
 * This class provides methods to display greeting and farewell messages, print task lists,
 * show task operation feedback, handle errors, and read user input from the console.
 * All output is formatted with decorative divider lines for visual consistency.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
public class Ui {
    private static final String LINE = "_________________________";

    /**
     * Prints the divider line.
     */
    public String showLine() {
        return LINE + "\n";
    }

    /**
     * Displays the startup greeting with Blue's ASCII art logo
     * followed by asking what the user needs help with.
     */
    public String greet() {
        String logo = """
                 ____  _           \s
                |  _ \\| |            \s
                | |_) | |_   _  ___   \s
                |  _ <| | | | |/ _ \\ \s
                | |_) | | |_| |  __/  \s
                |____/|_|\\__,_|\\___|\s
                """;

        String greeting = "Hi☆・*。It's me, Blue! \n"
                + "What do you need help with?";

        String message = logo + greeting;

        return wrapTextWithLines(message);
    }

    /**
     * Displays the farewell message when exiting the application.
     */
    public String bye() {
        String goodbye = "Byeee (^_^)/~ See you soon!";

        return showLine()
                + goodbye + "\n"
                + showLine();
    }

    /**
     * Wraps the given text with decorative divider lines above and below.
     *
     * @param str The message to display with line wrapping.
     */
    public String wrapTextWithLines(String str) {
        return showLine()
            + str + "\n"
            + showLine() + "\n";
    }

    /**
     * Prints a numbered list of tasks or an empty list message.
     *
     * @param tasks The list of tasks to display.
     * @throws BlueException if no tasks found
     */
    public String printList(ArrayList<Task> tasks) throws BlueException {
        if (tasks.isEmpty()) {
            throw new BlueException("Wow! There's nothing to do!");
        }
        String message = showLine();

        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }

        message += showLine() + "\n";
        return message;
    }

    /**
     * Shows confirmation message when a task is added to the list.
     *
     * @param task  The task that was added.
     * @param size  The total number of tasks after adding.
     */
    public String addTaskMessage(Task task, int size) {
        String message = "Okay! I'll add this task now! \n"
                + task + "\n"
                + String.format("You have %d tasks!", size);
        return wrapTextWithLines(message);
    }

    /**
     * Shows confirmation when a task is marked as done.
     *
     * @param tasks The tasks that were marked as completed.
     */
    public String taskMarkMessage(Task... tasks) {
        String message = "YAY it's now done!! ^o^ \n";
        for (Task task : tasks) {
            message += task + "\n";
        }
        return wrapTextWithLines(message);
    }

    /**
     * Shows confirmation when a task is unmarked (set back to not done).
     *
     * @param tasks The tasks that were unmarked.
     */
    public String taskUnmarkMessage(Task... tasks) {
        String message = "I thought you already did that ㅜ_ㅜ \n";
        for (Task task : tasks) {
            message += task + "\n";
        }
        return wrapTextWithLines(message);
    }

    /**
     * Shows confirmation when a task is deleted from the list.
     *
     * @param tasks The tasks that were deleted.
     */
    public String deleteTaskMessage(Task... tasks) {
        String message = "Okay, I deleted that for you! \n";
        for (Task task : tasks) {
            message += task + "\n";
        }
        return wrapTextWithLines(message);
    }

    /**
     * Shows confirmation that all tasks are deleted from the list.
     */
    public String deleteAllTasks() {
        String message = "Okay, I deleted everything!";
        return wrapTextWithLines(message);
    }

    /**
     * Shows confirmation that all tasks are marked.
     */
    public String markAllTasks() {
        String message = "Okay, I marked everything as done!";
        return wrapTextWithLines(message);
    }

    /**
     * Shows confirmation that all tasks are unmarked.
     */
    public String unmarkAllTasks() {
        String message = "Okay, I unmarked everything as done!";
        return wrapTextWithLines(message);
    }


    /**
     * Displays a numbered list of found tasks or throws an exception if none found.
     *
     * @param tasks List of tasks matching search criteria.
     * @throws BlueException if no tasks found (empty list).
     */
    public String showFoundTasks(ArrayList<Task> tasks) throws BlueException {
        if (tasks.isEmpty()) {
            throw new BlueException("Couldn't find any tasks with that keyword :(");
        }

        String message = showLine()
                + "Here are the tasks I found!\n";

        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }

        message += showLine() + "\n";
        return message;
    }

    /**
     * Displays an error message when task loading from storage fails.
     */
    public String showTaskLoadingError() {
        return wrapTextWithLines("Couldn't load tasks (╥﹏╥) Initialising with empty task list!");
    }
}
