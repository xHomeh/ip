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

    // Wrap string with lines on the top and bottom
    private static void wrapTextWithLines(String str) {
        String wrappedText = line
                + str + "\n"
                + line;
        System.out.println(wrappedText);
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
                int unmarkIdx = getMarkIdx(input);
                unmarkTask(unmarkIdx);
                break;
            case TODO:
                if (input.isEmpty()) {
                    throw new BlueException("The description can't be empty! =/");
                }
                addToDo(input);
                break;
            case DEADLINE:
                String[] deadlineInfo = getDeadlineInfo(input);
                addDeadline(deadlineInfo[0], deadlineInfo[1]);
                break;
            case EVENT:
                String[] eventInfo = getEventInfo(input);
                addEvent(eventInfo[0], eventInfo[1], eventInfo[2]);
                break;
            case DELETE:
                int deleteIdx = getMarkIdx(input);
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

    // helper function to handle input for DEADLINE case, returns a String array of length 2
    private static String[] getDeadlineInfo(String input) throws BlueException {
        String[] result = new String[2];
        if (input.isEmpty()) {
            throw new BlueException("The description can't be empty! =/");
        }
        String[] by = input.split("/by", 2);
        result[0] = by[0].trim();

        BlueException deadlineException = new BlueException("Deadlines must have a deadline... (ꐦ¬_¬)");

        if (by.length < 2) {
            throw deadlineException;
        }
        result[1] = by[1].trim();
        if (result[1].isEmpty()) {
            throw deadlineException;
        }
        return result;
    }

    // helper function to handle input for EVENT case, returns a String array of length 3
    private static String[] getEventInfo(String input) throws BlueException {
        String[] result = new String[3];
        if (input.isEmpty()) {
            throw new BlueException("The description can't be empty! =/");
        }
        String[] fromSplitArr = input.split("/from", 2);
        result[0] = fromSplitArr[0].trim();

        BlueException eventException = new BlueException("Events must have start and end times!!! <(˶`ロ´˶)> ");

        if (fromSplitArr.length < 2) {
            throw eventException;
        }
        String eventRemainder = fromSplitArr[1].trim();
        if (eventRemainder.isEmpty()) {
            throw eventException;
        }
        String[] toSplitArr = eventRemainder.split("/to", 2);
        result[1] = toSplitArr[0].trim();
        if (toSplitArr.length < 2) {
            throw eventException;
        }
        result[2] = toSplitArr[1].trim();
        if (result[2].isEmpty()) {
            throw eventException;
        }
        return result;
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
