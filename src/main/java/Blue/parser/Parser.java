package Blue.parser;

import Blue.exceptions.BlueException;
import Blue.command.*;

public class Parser {
    public static Command parseInput(String input) throws BlueException {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String arguments = (parts.length > 1) ? parts[1] : "";

        return switch (command) {
            case "exit", "bye", "quit", "q" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(arguments);
            case "unmark" -> new UnmarkCommand(arguments);
            case "todo" -> new AddToDoCommand(arguments);
            case "deadline" -> new AddDeadlineCommand(arguments);
            case "event" -> new AddEventCommand(arguments);
            case "delete" -> new DeleteCommand(arguments);
            default -> throw new BlueException("I don't know what you want me to do about that ㅠ.ㅠ");
        };
    }
}
