package blue.parser;

import blue.command.AddDeadlineCommand;
import blue.command.Command;
import blue.exceptions.BlueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseInput_unknownCommand_exceptionThrown() {
        BlueException exception = assertThrows(BlueException.class, () -> Parser.parseInput("wrong input"));
        assertEquals("I don't know what you want me to do about that ㅠ.ㅠ", exception.getMessage());
    }

    @Test
    public void parseInput_deadlineCommand_AddDeadlineCommandClass() throws BlueException {
        Command result = Parser.parseInput("deadline return books /by 2026 1 1");
        assertInstanceOf(AddDeadlineCommand.class, result);
    }
}
