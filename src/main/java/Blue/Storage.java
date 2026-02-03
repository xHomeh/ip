package Blue;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(String fileName) {
        this.filePath = Paths.get("data", fileName);
    }

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

    public Task convertToTask(String line) throws BlueException {
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
