package ru.petrov.lab4.utils.providers;

import ru.petrov.lab4.utils.Explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ExplorerProvider {

    private Explorer explorer;

    public ExplorerProvider(Explorer explorer) {
        this.explorer = explorer;
    }

    public void onListCommand(String commandParam) throws IOException {
        List<Path> content;
        if (commandParam.equals(".")) {
            content = explorer.getEverythingInDirectory();
        } else {
            content = explorer.getEverythingInDirectory(commandParam);
        }
        content.forEach(System.out::println);
    }

    public void onChangeDirectory(String commandParam) {
        Path path = Path.of(commandParam);
        explorer.moveTo(path);
    }

    public void onMakeDirectory(String commandParam) throws IOException {
        explorer.createDir(commandParam);
    }

    public void onCreateFile(String commandParam) throws IOException {
        explorer.createFile(commandParam);
    }

    public void onDelete(String commandParam) throws IOException {
        Path path = Path.of(commandParam);
        if (!path.isAbsolute()) {
            path = explorer.getCurrentPath().resolve(path);
        }
        explorer.delete(new File(path.toString()));
    }

    public Path getOpenedFilePath(String commandParam) {
        return explorer.getCurrentPath().resolve(commandParam);
    }

    public String getCurrentPath() {
        return explorer.getCurrentPath().toString();
    }
}
