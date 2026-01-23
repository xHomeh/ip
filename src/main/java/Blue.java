import java.util.ArrayList;
import java.util.Scanner;

public class Blue {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String line = "__________________________________________________ \n";

    // Prints the greeting message when the chatbot starts running
    private static void greet() {
        String logo = " ____  _            \n"
                + "|  _ \\| |             \n"
                + "| |_) | |_   _  ___    \n"
                + "|  _ <| | | | |/ _ \\  \n"
                + "| |_) | | |_| |  __/   \n"
                + "|____/|_|\\__,_|\\___| \n";
        System.out.print(logo);

        String greeting = line
                    + "Hi☆・*。It's me, Blue! \n"
                    + "What do you need help with? \n"
                    + line;
        System.out.print(greeting);
    }

    // Prints the goodbye message when the chatbot is quit
    private static void bye() {
        String goodbye = line
                    + "Byeee (^_^)/~ See you soon! \n"
                    + line;
        System.out.print(goodbye);
    }

    // Returns a boolean and checks if the user typed either quit, bye, or exit to quit the chatbot
    private static boolean checkExitInput(String input) {
        return input.equalsIgnoreCase("bye")
                || input.equalsIgnoreCase("quit")
                || input.equalsIgnoreCase("exit")
                || input.equalsIgnoreCase("q");
    }

    // Wrap string with lines on the top and bottom
    private static void wrapTextWithLines(String str) {
        String wrappedText = line
                + str + "\n"
                + line;
        System.out.println(wrappedText);
    }

    // Add new task to taskList
    private static void addTask(String input) {
        Task task = new Task(input);
        taskList.add(task);
        String message = "Added: " + input;
        wrapTextWithLines(message);
    }

    // print out the list of stored text
    private static void printList() {
        int size = taskList.size();

        System.out.print(line);

        for (int i = 0; i < size; i++) {
            String message = (i+1) + ". " + taskList.get(i).toString();
            System.out.println(message);
        }

        System.out.println(line);
    }

    private static void markTask(int idx) {
        Task task = taskList.get(idx-1);
        task.markDone();
        String message = "YAY this task is now done!! ^o^ \n"
                    + task.toString();
        wrapTextWithLines(message);
    }

    private static void unmarkTask(int idx) {
        Task task = taskList.get(idx-1);
        task.unmarkDone();
        String message = "I thought you already did that ㅜ_ㅜ \n"
                + task.toString();
        wrapTextWithLines(message);
    }

    // Handles the commands inputted to the chatbot
    private static void handleInput(String command, String desc) {
        if (command.equalsIgnoreCase("list")) {
            printList();
        } else if (command.equalsIgnoreCase("mark")) {
            markTask(Integer.parseInt(desc));
        } else if (command.equalsIgnoreCase("unmark")) {
            unmarkTask(Integer.parseInt(desc));
        } else {
            addTask(command);
        }
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();   // reads user input. trim to remove whitespace at front n end of message
            String[] parts = input.split("\\s+", 2);    // split text into first word (command) and second part which is rest of the message
            String command = parts[0];  // first word
            String desc = (parts.length > 1) ? parts[1] : "";   // rest of the message otherwise empty string

            // check if an exit command was given to quit the bot
            if (checkExitInput(command)) {
                bye();
                break;
            }

            handleInput(command, desc);
        }
    }
}
