import java.util.ArrayList;
import java.util.Scanner;

public class Blue {
    private static class Task {
        private String name;

        private Task(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

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
                    + "Byeee~ See you soon! \n"
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
        System.out.print(wrappedText);
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

        for (int i = 0; i < size + 0; i++) {
            String message = (i+1) + ". " + taskList.get(i).toString();
            System.out.println(message);
        }

        System.out.print(line);
    }

    // Handles the commands inputted to the chatbot
    private static void handleInput(String input) {
        if (input.equalsIgnoreCase("list")) {
            printList();
        } else {
            addTask(input);
        }
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (checkExitInput(input)) {
                bye();
                break;
            }

            handleInput(input);
        }
    }
}
