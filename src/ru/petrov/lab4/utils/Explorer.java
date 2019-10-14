package ru.petrov.lab4.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Explorer {

    private static final String DEFAULT_PATH = "";

    private Path currentPath;

    public Explorer() {
        currentPath = Paths.get(DEFAULT_PATH).toAbsolutePath();
    }

    public Explorer(String path) {
        this.currentPath = Path.of(path).toAbsolutePath();
    }

    public Explorer(Path path) {
        this.currentPath = path.toAbsolutePath();
    }

    public Path getCurrentPath() {
        try {
            return currentPath.toRealPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Path> getEverythingInDirectory() throws IOException {
        return getEverythingInDirectory(currentPath);
    }

    public List<Path> getEverythingInDirectory(String path) throws IOException {
        return getEverythingInDirectory(Path.of(path));
    }

    public List<Path> getEverythingInDirectory(Path path) throws IOException {
        return Files.walk(path, 1).skip(1).map(path::relativize).collect(Collectors.toList());
    }

    public void moveTo(String path) {
        moveTo(currentPath.resolve(path));
    }

    public void moveTo(Path path) {
        Path newPath;
        if (path.isAbsolute()) {
            newPath = path;
        } else {
            newPath = currentPath.resolve(path);
        }
        if (Files.notExists(newPath)) {
            throw new IllegalArgumentException("Path " + path.toString() + " does not exist");
        }
        if (!Files.isDirectory(newPath)) {
            throw new IllegalArgumentException("Path " + path.toString() + " is not a directory");
        }
        currentPath = newPath.toAbsolutePath();
    }

    public void createDir(String dirName) throws IOException {
        createDir(currentPath.resolve(dirName));
    }

    public void createDir(Path path) throws IOException {
        Files.createDirectory(path);
    }

    public void createFile(String fileName) throws IOException {
        createFile(currentPath.resolve(fileName));
    }

    public void createFile(Path path) throws IOException {
        Files.createFile(path);
    }

    public void delete(File f) throws IOException {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files) {
                    delete(file);
                }
            }
        }
        Files.delete(f.toPath());
    }

}
