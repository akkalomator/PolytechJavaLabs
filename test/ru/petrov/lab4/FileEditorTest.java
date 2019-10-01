package ru.petrov.lab4;

import org.junit.jupiter.api.Test;
import ru.petrov.lab4.utils.FileEditor;
import ru.petrov.lab4.utils.SaveMode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileEditorTest {

    private static final String INITIAL_PATH = "resources/lab4";

    @Test
    void openFile_ThrowsWhenFileDoesNotExists() throws Exception {
        try (FileEditor editor = new FileEditor(Path.of(INITIAL_PATH + "/c"))) {
            assertThrows(FileNotFoundException.class, editor::open);
        }
    }

    @Test
    void readContent_ThrowsOnEditorNotOpened() throws Exception {
        try (FileEditor editor = new FileEditor(Path.of(INITIAL_PATH + "/a/file2.file"))) {
            assertThrows(IllegalStateException.class, editor::readContent);
        }
    }

    @Test
    void readContent_WorksCorrectly() throws Exception {
        List<String> expected = List.of(
            "1st line",
            "2nd line",
            "3rd line"
        );
        List<String> lines;
        try (FileEditor editor = new FileEditor(Path.of(INITIAL_PATH + "/a/file2.file"))) {
            editor.open();
            lines = editor.readContent();
        }

        assertEquals(expected, lines);
    }

    @Test
    void appendData_ThrowsOnEditorHasNotBeenOpened() throws Exception {
        try (FileEditor editor = new FileEditor(Path.of(INITIAL_PATH + "/a/file2.file"))) {
            assertThrows(IllegalStateException.class, () -> editor.appendData("lol"));
        }
    }

    @Test
    void save_ThrowsOnEditorHasNotBeenOpened() throws Exception {
        try (FileEditor editor = new FileEditor(Path.of(INITIAL_PATH + "/a/file2.file"))) {
            assertThrows(IllegalStateException.class, () -> editor.save(SaveMode.APPEND));
        }
    }

    @Test
    void appendData_and_save_WorksCorrectly() throws Exception {
        String pathToFile = INITIAL_PATH + "/a/file1.file";
        Files.deleteIfExists(Path.of(pathToFile));
        Files.createFile(Path.of(pathToFile));
        try (FileEditor editor = new FileEditor(Path.of(pathToFile))) {
            editor.open();
            String str1 = "12345";
            editor.appendData(str1);
            String str2 = "abcdef";
            editor.appendData(str2);
            String str3 = "dn324j234";
            editor.appendData(str3);
            editor.save(SaveMode.REWRITE);

            FileReader reader = new FileReader(pathToFile);
            BufferedReader br = new BufferedReader(reader);

            assertEquals(str1, br.readLine());
            assertEquals(str2, br.readLine());
            assertEquals(str3, br.readLine());
        }
    }
}