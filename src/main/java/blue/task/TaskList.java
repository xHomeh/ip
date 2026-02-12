package blue.task;

import java.util.ArrayList;

/**
 * Manages a collection of Task objects for the Blue task management system.
 * <p>
 * Provides standard list operations (add, remove, get, size) with 0-based indexing.
 * Used as the central data model passed between commands, storage, and UI components.
 * Supports both empty initialization and loading from persisted data.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with existing tasks.
     *
     * @param tasks Preloaded Task objects from storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "TaskList should not store null tasks!";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task The Task to add.
     */
    public void add(Task task) {
        assert task != null : "TaskList should not store null tasks!";
        tasks.add(task);
    }

    /**
     * Removes a task at the specified 0-based index.
     *
     * @param idx 0-based index of task to remove.
     */
    public void remove(int idx) {
        assert idx >= 0 && idx < tasks.size() : "Index must refer to an existing task before removal!";
        tasks.remove(idx);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Current size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task at the specified 0-based index.
     *
     * @param idx 0-based index of task to retrieve.
     * @return Task at the specified position.
     */
    public Task get(int idx) {
        assert idx >= 0 && idx < tasks.size() : "Index must refer to an existing task before retrieving!";
        Task task = tasks.get(idx);
        assert task != null : "TaskList should not store null tasks!";
        return task;
    }

    /**
     * Returns the complete list of tasks.
     *
     * @return An ArrayList containing all the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword.
     *
     * @param str Keyword to search for in task descriptions.
     * @return List of tasks containing the keyword (case-insensitive match).
     */
    public ArrayList<Task> findTasks(String str) {
        assert str != null : "Find keyword should not be null!";
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            assert t != null : "TaskList should not store null tasks!";
            if (t.getDescription().toLowerCase().contains(str.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }
}
