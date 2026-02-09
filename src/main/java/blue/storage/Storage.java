package blue.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import blue.exceptions.BlueException;
import blue.task.Deadline;
import blue.task.Event;
import blue.task.Task;
import blue.task.TaskList;
import blue.task.ToDo;

/**
 * Manages persistent storage of tasks to and from file system.
 * <p>
 * This class handles reading tasks from a specified file during startup and
 * saving the current task list back to file after modifications.
 * The storage directory is created automatically if it doesn't exist.
 * </p>
 *
 * @author xHomeh / Joel Wong
 */
public class Storage {
    private final Path filePath;

    /**
     * Initializes storage with the specified filename.
     *
     * @param fileName The name of the file to store tasks (e.g., "Blue.txt").
     */
    public Storage(String fileName) {
        filePath = Paths.get("data", fileName);
    }

    /**
     * Loads all tasks from the storage file.
     * <p>
     * Returns an empty list if the file doesn't exist. Throws {@link BlueException}
     * if file reading fails or data is corrupted/invalid.
     * </p>
     *
     * @return List of Task objects loaded from file.
     * @throws BlueException if IO error occurs or data format is invalid.
     */
    public ArrayList<Task> load() throws BlueException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                tasks.add(convertToTask(line));
            }
        } catch (IOException e) {
            throw new BlueException("Failed to load tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the current task list to the storage file.
     * <p>
     * Overwrites the entire file with the current tasks using their storage format.
     * Creates the parent directory if it doesn't exist. Throws BlueException
     * if file writing fails.
     * </p>
     *
     * @param taskList The TaskList containing tasks to save.
     * @throws BlueException if IO error occurs during file writing.
     */
    public void save(TaskList taskList) throws BlueException {
        try {
            Files.createDirectories(filePath.getParent());

            List<String> lines = new ArrayList<>();

            for (Task t : taskList.getTasks()) {
                lines.add(t.toStorageString());
            }

            Files.write(
                    filePath,
                    lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
            throw new BlueException("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a single line of stored data back into a Task object.
     *
     * @param line Single line from storage file representing one task.
     * @return Reconstructed Task object.
     * @throws BlueException if line format is corrupted or contains unknown task type.
     */
    private Task convertToTask(String line) throws BlueException {
        String[] parts = line.split("\\s*\\|\\s*");

        if (parts.length < 3) {
            throw new BlueException("Saved data is corrupted: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch(type) {
        case "T":
            task = new ToDo(description);
            break;

        case "D":
            if (parts.length < 4) {
                throw new BlueException("Saved data is corrupted: " + line);
            }
            task = new Deadline(description, parts[3]);
            break;

        case "E":
            if (parts.length < 5) {
                throw new BlueException("Saved data is corrupted: " + line);
            }
            task = new Event(description, parts[3], parts[4]);
            break;

        default:
            throw new BlueException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }
}
