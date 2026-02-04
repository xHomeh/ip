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
package blue.ui;

import blue.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "__________________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the startup greeting with Blue's ASCII art logo
     * followed by asking what the user needs help with.
     */
    public void greet() {
        String logo = """
                 ____  _           \s
                |  _ \\| |            \s
                | |_) | |_   _  ___   \s
                |  _ <| | | | |/ _ \\ \s
                | |_) | | |_| |  __/  \s
                |____/|_|\\__,_|\\___|\s
                """;
        System.out.print(logo);

        String greeting = "Hi☆・*。It's me, Blue! \n"
                + "What do you need help with?";

        wrapTextWithLines(greeting);
    }

    /**
     * Displays the farewell message when exiting the application.
     */
    public void bye() {
        String goodbye = "Byeee (^_^)/~ See you soon!";

        showLine();
        System.out.println(goodbye);
        showLine();
    }

    /**
     * Prints the command prompt indicator to indicate user
     * can enter a command.
     */
    public void commandLine() {
        System.out.print("> ");
    }

    /**
     * Reads a command from the user via console input.
     *
     * @return The user's input command as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Wraps the given text with decorative divider lines above and below.
     *
     * @param str The message to display with line wrapping.
     */
    public void wrapTextWithLines(String str) {
        showLine();
        System.out.println(str);
        showLine();
        System.out.println();
    }

    /**
     * Prints a numbered list of tasks or an empty list message.
     *
     * @param tasks The list of tasks to display.
     */
    public void printList(ArrayList<Task> tasks) {
        int size = tasks.size();
        if (size == 0) {
            wrapTextWithLines("Wow! There's nothing to do!");
            return;
        }

        showLine();

        for (int i = 0; i < size; i++) {
            String message = (i+1) + ". " + tasks.get(i).toString();
            System.out.println(message);
        }

        showLine();
        System.out.println();
    }

    /**
     * Shows confirmation message when a task is added to the list.
     *
     * @param task  The task that was added.
     * @param size  The total number of tasks after adding.
     */
    public void addTaskMessage(Task task, int size) {
        String message = "Okay! I'll add this task now! \n"
                + task + "\n"
                + String.format("You have %d tasks!", size);
        wrapTextWithLines(message);
    }

    /**
     * Shows confirmation when a task is marked as done.
     *
     * @param task The task that was marked as completed.
     */
    public void taskMarkMessage(Task task) {
        String message = "YAY this task is now done!! ^o^ \n"
                + task;
        wrapTextWithLines(message);
    }

    /**
     * Shows confirmation when a task is unmarked (set back to not done).
     *
     * @param task The task that was unmarked.
     */
    public void taskUnmarkMessage(Task task) {
        String message = "Okay, I deleted that for you! \n"
                + task;
        wrapTextWithLines(message);
    }

    /**
     * Shows confirmation when a task is deleted from the list.
     *
     * @param task The task that was deleted.
     */
    public void deleteTaskMessage(Task task) {
        String message = "Okay, I deleted that for you! \n"
                + task;
        wrapTextWithLines(message);
    }

    /**
     * Displays an error message when task loading from storage fails.
     */
    public void showTaskLoadingError() {
        wrapTextWithLines("Couldn't load tasks (╥﹏╥) Initialising with empty task list!");
    }
}
