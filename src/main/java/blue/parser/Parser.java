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
package blue.parser;

import blue.exceptions.BlueException;
import blue.command.*;

public class Parser {

    /**
     * Parses raw user input into the appropriate Command object.
     *
     * @param input Raw user input string from console.
     * @return Command instance for execution.
     * @throws BlueException if command is unknown or invalid.
     */
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
            case "find" -> new findCommand(arguments);
            default -> throw new BlueException("I don't know what you want me to do about that ㅠ.ㅠ");
        };
    }
}
