package blue.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import blue.command.AddDeadlineCommand;
import blue.command.Command;
import blue.exceptions.BlueException;

public class ParserTest {
    @Test
    public void parseInput_unknownCommand_exceptionThrown() {
        BlueException exception = assertThrows(BlueException.class, () -> Parser.parseInput("wrong input"));
        assertEquals("I don't know what you want me to do about that ㅠ.ㅠ", exception.getMessage());
    }

    @Test
    public void parseInput_blankInput_exceptionThrown() {
        BlueException exception = assertThrows(BlueException.class, () -> Parser.parseInput("   "));
        assertEquals("Please enter a command so I can help you!", exception.getMessage());
    }

    @Test
    public void parseInput_deadlineCommand_addDeadlineCommandClass() throws BlueException {
        Command result = Parser.parseInput("deadline return books /by 2026 1 1");
        assertInstanceOf(AddDeadlineCommand.class, result);
    }
}
