public class Ui {
    private static final String line = "__________________________________________________ \n";

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

    // Wrap string with lines on the top and bottom
    public void wrapTextWithLines(String str) {
        String wrappedText = line
                + str + "\n"
                + line;
        System.out.println(wrappedText);
    }

    private void addTaskMessage(Task task, int size) {
        String message = "Okay! I'll add this task now! \n"
                + task + "\n"
                + String.format("You have %d tasks!", size);
        wrapTextWithLines(message);
    }

    public void showTaskLoadingError() {
        wrapTextWithLines("Couldn't load tasks (╥﹏╥) Initialising with empty task list!");
    }
}
