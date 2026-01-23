public enum Command {
    BYE,
    QUIT,
    EXIT,
    Q,
    LIST,
    MARK,
    UNMARK,
    UNKNOWN;

    public boolean isExitCommand() {
        return switch (this) {
            case EXIT, BYE, QUIT, Q -> true;
            default -> false;
        };
    }

    public static Command check(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

