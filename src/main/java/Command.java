public enum Command {
    BYE,
    QUIT,
    EXIT,
    Q,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    UNKNOWN;

    // checks if the command given is an exit command to quit the chatbot
    public boolean isExitCommand() {
        return switch (this) {
            case EXIT, BYE, QUIT, Q -> true;
            default -> false;
        };
    }

    // changes input string to a Command
    public static Command check(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

