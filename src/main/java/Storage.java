import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final Path FILE_PATH;

    public Storage(String fileName) {
        this.FILE_PATH = Paths.get("data", fileName);
    }

    public List<Task> load() {
        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    public void save(ArrayList<Task> tasks) {
        try {
            Files.createDirectories(FILE_PATH.getParent());

            List<String> lines = new ArrayList<>();

            for (Task t : tasks) {
                lines.add(t.toStorageString());
            }

            Files.write(
                    FILE_PATH,
                    lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
            System.out.println("Failed to save tasks.");
        }
    }
}
