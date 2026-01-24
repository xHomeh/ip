import java.util.ArrayList;
import java.util.Scanner;

public class Blue {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String line = "__________________________________________________ \n";

    // Prints the greeting message when the chatbot starts running
    private static void greet() {
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
    private static void bye() {
        String goodbye = line
                + "Byeee (^_^)/~ See you soon! \n"
                + line;
        System.out.print(goodbye);
    }

    // Returns a boolean and checks if the user typed either quit, bye, or exit to quit the chatbot
    private static boolean checkExitInput(Command input) {
        return input.equals(Command.EXIT)
                || input.equals(Command.BYE)
                || input.equals(Command.QUIT)
                || input.equals(Command.Q);
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

    // message printed when a new task is added
    private static void addTaskMessage(Task task) {
        String message = "Okay! I'll add this task now! \n"
                    + task.toString() + "\n"
                    + String.format("You have %d tasks!", taskList.size());
        wrapTextWithLines(message);
    }

    // add new ToDo to the list
    private static void addToDo(String input) {
        Task task = new ToDo(input);
        taskList.add(task);
        addTaskMessage(task);
    }

    // add new Deadline to the list
    private static void addDeadline(String desc, String due) {
        Task task = new Deadline(desc, due);
        taskList.add(task);
        addTaskMessage(task);
    }

    // add new Event to the list
    private static void addEvent(String desc, String to, String from) {
        Task task = new Event(desc, to, from);
        taskList.add(task);
        addTaskMessage(task);
    }

    // print out the list of stored text
    private static void printList() {
        int size = taskList.size();

        if (size == 0) {
            wrapTextWithLines("Wow! There's nothing to do!");
            return;
        }

        System.out.print(line);

        for (int i = 0; i < size; i++) {
            String message = (i+1) + ". " + taskList.get(i).toString();
            System.out.println(message);
        }

        System.out.println(line);
    }

    // mark task as done
    private static void markTask(int idx) {
        Task task = taskList.get(idx-1);
        task.markDone();
        String message = "YAY this task is now done!! ^o^ \n"
                + task;
        wrapTextWithLines(message);
    }

    // mark task as undone
    private static void unmarkTask(int idx) {
        Task task = taskList.get(idx-1);
        task.unmarkDone();
        String message = "I thought you already did that ㅜ_ㅜ \n"
                + task;
        wrapTextWithLines(message);
    }

    // message for unknown commands
    private static void printErrorMsg() {
        String message = "I don't know what you want me to do about that ㅠ.ㅠ";
        wrapTextWithLines(message);
    }

    // Handles the commands inputted to the chatbot
    private static void handleInput(Command command, String input) throws BlueException {
        switch (command) {
            case LIST:
                printList();
                break;
            case MARK:
                int markIdx = getMarkIdx(input);
                markTask(markIdx);
                break;
            case UNMARK:
                unmarkTask(Integer.parseInt(input));
                break;
            case TODO:
                if (input.isEmpty()) {
                    throw new BlueException("The description can't be empty! =/");
                }
                addToDo(input);
                break;
            case DEADLINE:
                String[] by = input.split("/by ", 2);
                String deadlineDesc = by[0].trim();
                String due = by[1].trim();
                addDeadline(deadlineDesc, due);
                break;
            case EVENT:
                String[] fromSplitArr = input.split("/from ", 2);
                String eventDesc = fromSplitArr[0].trim();
                String eventRemainder = fromSplitArr[1].trim();
                String[] toSplitArr = eventRemainder.split("/to ", 2);
                String from = toSplitArr[0].trim();
                String to = toSplitArr[1].trim();
                addEvent(eventDesc, from, to);
                break;
            case UNKNOWN:
                printErrorMsg();
                break;
        }
    }

    // helper function to handle input for MARK case
    private static int getMarkIdx(String input) throws BlueException {
        int markIdx;
        try {
            markIdx = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BlueException("Give me a number ( ｡ •̀ ᴖ •́ ｡)");
        }
        if (markIdx <= 0) {
            throw new BlueException("Number must be positive!!! ୧(๑•̀ᗝ•́)૭");
        }
        if (markIdx > taskList.size()) {
            throw new BlueException("There isn't a task " + markIdx + "!  (•̀⤙•́ )");
        }
        return markIdx;
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String userTextInput = scanner.nextLine().trim();   // reads user input. trim to remove whitespace at front n end of message
            String[] parts = userTextInput.split("\\s+", 2);    // split text into first word (command) and second part which is rest of the message
            Command command = Command.check(parts[0]);  // first word
            String input = (parts.length > 1) ? parts[1] : "";   // rest of the message otherwise empty string

            // check if an exit command was given to quit the bot
            if (command.isExitCommand()) {
                bye();
                break;
            }
            try {
                handleInput(command, input);
            } catch (BlueException e) {
                String errorMessage = e.getMessage();
                wrapTextWithLines(errorMessage);
            }
        }
    }
}
