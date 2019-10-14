package ru.petrov.lab4.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileEditor implements AutoCloseable {

    private static final String EDITOR_NOT_OPENED_EXCEPTION_MESSAGE = "File editor has not been opened yet";

    private BufferedReader reader;
    private Path filePath;
    private boolean isOpened;
    private StringBuilder builder;

    public FileEditor(Path filePath) {
        this.filePath = filePath.toAbsolutePath();
        this.isOpened = false;
    }

    public void open() throws FileNotFoundException {
        isOpened = true;
        reader = new BufferedReader(new FileReader(filePath.toString()));
        builder = new StringBuilder();
    }

    public List<String> readContent() {
        if (!isOpened) {
            throw new IllegalStateException(EDITOR_NOT_OPENED_EXCEPTION_MESSAGE);
        }
        return reader.lines().collect(Collectors.toList());
    }

    public void appendData(String str) {
        if (!isOpened) {
            throw new IllegalStateException(EDITOR_NOT_OPENED_EXCEPTION_MESSAGE);
        }
        builder.append(str).append("\n");
    }

    public void save(SaveMode saveMode) throws IOException {
        if (!isOpened) {
            throw new IllegalStateException(EDITOR_NOT_OPENED_EXCEPTION_MESSAGE);
        }
        if (saveMode == SaveMode.APPEND) {
            try (FileWriter writer = new FileWriter(filePath.toString(), true)) {
                writer.write(builder.toString());
            }
        } else if (saveMode ==SaveMode.REWRITE){

            try (FileWriter writer = new FileWriter(filePath.toString())) {
                writer.write(builder.toString());
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
        isOpened = false;
    }

    public String getName() {
        return filePath.toString();
    }
}
