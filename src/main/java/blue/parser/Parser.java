package blue.parser;

import blue.command.AddDeadlineCommand;
import blue.command.AddEventCommand;
import blue.command.AddToDoCommand;
import blue.command.Command;
import blue.command.DeleteCommand;
import blue.command.ExitCommand;
import blue.command.FindCommand;
import blue.command.ListCommand;
import blue.command.MarkCommand;
import blue.command.UnmarkCommand;
import blue.exceptions.BlueException;

/**
 * Parses user input strings into corresponding Command objects.
 * <p>
 * This class serves as the central input processor for the Blue chatbot, mapping
 * user commands to specific command implementations. It splits input into command
 * type and arguments, then instantiates the appropriate command handler.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
public class Parser {

    /**
     * Parses raw user input into the appropriate Command object.
     *
     * @param input Raw user input string from console.
     * @return Command instance for execution.
     * @throws BlueException if command is unknown or invalid.
     */
    public static Command parseInput(String input) throws BlueException {
        assert input != null : "Parser expects a non-null input string.";
        if (input.trim().isEmpty()) {
            throw new BlueException("Please enter a command so I can help you!");
        }

        String[] parts = input.split("\\s+", 2);
        assert parts.length >= 1 : "Split command input should always produce at least one token.";

        String command = parts[0].toLowerCase();
        String arguments = (parts.length > 1) ? parts[1] : "";

        assert arguments != null : "Parsed command arguments should default to an empty string.";

        return switch (command) {
        case "exit", "bye", "quit", "q" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(arguments);
        case "unmark" -> new UnmarkCommand(arguments);
        case "todo" -> new AddToDoCommand(arguments);
        case "deadline" -> new AddDeadlineCommand(arguments);
        case "event" -> new AddEventCommand(arguments);
        case "delete" -> new DeleteCommand(arguments);
        case "find" -> new FindCommand(arguments);
        default -> throw new BlueException("I don't know what you want me to do about that ㅠ.ㅠ");
        };
    }
}
