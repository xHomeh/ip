package blue.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void findTasks_singleKeyword_caseInsensitiveMatch() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Read API docs"));
        taskList.add(new ToDo("buy groceries"));

        ArrayList<Task> foundTasks = taskList.findTasks("read");

        assertEquals(1, foundTasks.size());
        assertEquals("Read API docs", foundTasks.get(0).getDescription());
    }

    @Test
    public void findTasks_multipleKeywords_allKeywordsMustMatch() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("prepare project demo slides"));
        taskList.add(new ToDo("prepare team lunch"));
        taskList.add(new ToDo("demo chatbot"));

        ArrayList<Task> foundTasks = taskList.findTasks("prepare demo");

        assertEquals(1, foundTasks.size());
        assertEquals("prepare project demo slides", foundTasks.get(0).getDescription());
    }
}
