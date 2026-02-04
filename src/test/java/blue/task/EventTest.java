package blue.task;

import blue.Blue;
import blue.exceptions.BlueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void Event_EndDateBeforeStartDate_ExceptionThrown() {
        BlueException exception = assertThrows(BlueException.class, () -> new Event("Test", "2026 2 2", "2026 1 1"));
        assertEquals("Events must end after they start!!!", exception.getMessage());
    }

    @Test
    public void toString_newEvent_correctString() throws BlueException {
        Event e = new Event("seminar", "2026 2 10", "2026 2 15");
        assertEquals("[E][ ] seminar (from: 10 Feb 2026 to: 15 Feb 2026)", e.toString());
    }
}
