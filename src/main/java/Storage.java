import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public void save(List<Task> tasks) {

    }
}
