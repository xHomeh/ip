package blue.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int idx) {
        tasks.remove(idx);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

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
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(str.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }
}
