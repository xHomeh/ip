package blue.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * Removes tasks at the specified 0-based indices.
     *
     * @param indices Array of 0-based index of task to remove.
     */
    public void remove(int... indices) {
        assert indices != null && indices.length > 0 : "Must pass at least one index!";

        Arrays.sort(indices);
        for (int i = indices.length - 1; i >= 0; i--) {
            int idx = indices[i];
            assert idx >= 0 && idx < tasks.size() : "Index must refer to an existing task before removal!";
            tasks.remove(idx);
        }
    }

    /**
     * Resets list to be empty.
     */
    public void removeAll() {
        tasks = new ArrayList<>();
    }

    /**
     * Marks tasks at the specified 0-based indices as done.
     *
     * @param indices Array of 0-based index of task to mark as done.
     */
    public void mark(int... indices) {
        assert indices != null && indices.length > 0 : "Must pass at least one index!";

        for (int idx : indices) {
            assert idx >= 0 && idx < tasks.size() : "Index must refer to an existing task before removal!";
            tasks.get(idx).markDone();
        }
    }

    /**
     * Marks all tasks as done.
     */
    public void markAll() {
        for (Task task : tasks) {
            task.markDone();
        }
    }

    /**
     * Unmarks tasks at the specified 0-based indices as done.
     *
     * @param indices Array of 0-based index of task to unmark as done.
     */
    public void unmark(int... indices) {
        assert indices != null && indices.length > 0 : "Must pass at least one index!";

        for (int idx : indices) {
            assert idx >= 0 && idx < tasks.size() : "Index must refer to an existing task before removal!";
            tasks.get(idx).unmarkDone();
        }
    }

    /**
     * Unmarks all tasks as done.
     */
    public void unmarkAll() {
        for (Task task : tasks) {
            task.unmarkDone();
        }
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
     * @param indices Array of 0-based index of task to retrieve.
     * @return Task at the specified position.
     */
    public Task[] get(int... indices) {
        assert indices != null && indices.length > 0 : "Must pass at least one index!";

        Task[] result = new Task[indices.length];

        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            assert idx >= 0 && idx < tasks.size() : "Index must refer to an existing task before retrieving!";
            Task task = tasks.get(idx);
            assert task != null : "TaskList should not store null tasks!";
            result[i] = task;
        }
        return result;
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
     * Finds all tasks whose descriptions contain every keyword in the query.
     *
     * @param query Search query containing one or more keywords.
     * @return List of tasks containing all keywords (case-insensitive match).
     */
    public ArrayList<Task> findTasks(String query) {
        assert query != null : "Find query should not be null!";

        List<String> keywords = Arrays.stream(query.trim().toLowerCase().split("\\s+"))
                .filter(keyword -> !keyword.isBlank())
                .toList();

        return tasks.stream()
                .filter(task -> containsAnyKeyword(task.getDescription(), keywords))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks whether the given description contains at least one of the
     * specified keywords.
     * <p>
     * The comparison is case-insensitive and matches keywords as substrings
     * within the description.
     *
     * @param description The task description to search within.
     * @param keywords A list of keywords to match against.
     * @return {@code true} if the description contains any keyword;
     *         {@code false} otherwise.
     */
    private boolean containsAnyKeyword(String description, List<String> keywords) {
        String lowerCaseDescription = description.toLowerCase();
        return keywords.stream().anyMatch(lowerCaseDescription::contains);
    }
}
