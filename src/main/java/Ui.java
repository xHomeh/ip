import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String line = "__________________________________________________ \n";

    private final Scanner scanner = new Scanner(System.in);

    // Prints the greeting message when the chatbot starts running
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

        String greeting = line
                + "Hi☆・*。It's me, Blue! \n"
                + "What do you need help with? \n"
                + line;
        System.out.println(greeting);
    }

    // Prints the goodbye message when the chatbot is quit
    public void bye() {
        String goodbye = line
                + "Byeee (^_^)/~ See you soon! \n"
                + line;
        System.out.print(goodbye);
    }

    public void commandLine() {
        System.out.println("> ");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    // Wrap string with lines on the top and bottom
    public void wrapTextWithLines(String str) {
        String wrappedText = line
                + str + "\n"
                + line;
        System.out.println(wrappedText);
    }

    public void printList(ArrayList<Task> tasks) {
        int size = tasks.size();
        if (size == 0) {
            wrapTextWithLines("Wow! There's nothing to do!");
            return;
        }

        System.out.print(line);

        for (int i = 0; i < size; i++) {
            String message = (i+1) + ". " + tasks.get(i).toString();
            System.out.println(message);
        }

        System.out.println(line);
    }

    public void addTaskMessage(Task task, int size) {
        String message = "Okay! I'll add this task now! \n"
                + task + "\n"
                + String.format("You have %d tasks!", size);
        wrapTextWithLines(message);
    }

    public void taskMarkMessage(Task task) {
        String message = "YAY this task is now done!! ^o^ \n"
                + task;
        wrapTextWithLines(message);
    }

    public void taskUnmarkMessage(Task task) {
        String message = "Okay, I deleted that for you! \n"
                + task;
        wrapTextWithLines(message);
    }

    public void deleteTaskMessage(Task task) {
        String message = "Okay, I deleted that for you! \n"
                + task;
        wrapTextWithLines(message);
    }

    public void showTaskLoadingError() {
        wrapTextWithLines("Couldn't load tasks (╥﹏╥) Initialising with empty task list!");
    }
}
