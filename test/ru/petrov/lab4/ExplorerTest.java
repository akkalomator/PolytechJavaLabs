package ru.petrov.lab4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.petrov.lab4.utils.Explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExplorerTest {

    private static final String INITIAL_PATH = "resources/lab4";
    private Explorer explorer;

    @BeforeEach
    void init() throws IOException {
        explorer = new Explorer(INITIAL_PATH);
    }


    @Test
    void constructor_WorksCorrectly() throws IOException {
        assertEquals(Path.of(""), new Explorer().getCurrentPath());   // Path is set to path of executable

        assertEquals(Path.of("C:/"), new Explorer("C:/").getCurrentPath());
    }

    @Test
    void getFilesAtDirectory_WorksCorrectly() throws IOException {
        List<Path> inDirectory = List.of(
            Path.of("a"),
            Path.of("b"),
            Path.of("c")
        );
        assertEquals(inDirectory, explorer.getEverythingInDirectory());
    }

    @Test
    void moveTo_ThrowsWhenPathDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> explorer.moveTo("a/a"));
    }

    @Test
    void moveTo_ThrowsWhenPathIsNotADirectory() {
        assertThrows(IllegalArgumentException.class, () -> explorer.moveTo("a/a"));
    }

    @Test
    void moveTo_ThrowsWhenPathIsFile() {
        assertThrows(IllegalArgumentException.class, () -> explorer.moveTo("a/file1.file"));
    }

    @Test
    void moveTo_StringPath_WorksCorrectly() throws IOException {
        explorer.moveTo("a");
        assertEquals(
            Path.of("a"),
            Path.of(INITIAL_PATH).toAbsolutePath().relativize(explorer.getCurrentPath())
        );

        List<Path> inDirectory = List.of(
            Path.of("file1.file"),
            Path.of("file2.file"),
            Path.of("file3.file")
        );

        assertEquals(inDirectory, explorer.getEverythingInDirectory());
    }

    @Test
    void moveTo_RelativePath_WorksCorrectly() throws IOException {
        explorer.moveTo(Path.of("a"));
        assertEquals(
            Path.of("a"),
            Path.of(INITIAL_PATH).toAbsolutePath().relativize(explorer.getCurrentPath())
        );

        List<Path> inDirectory = List.of(
            Path.of("file1.file"),
            Path.of("file2.file"),
            Path.of("file3.file")
        );

        assertEquals(inDirectory, explorer.getEverythingInDirectory());
    }

    @Test
    void moveTo_RelativePath_MoveBack_WorksCorrectly() throws IOException {
        explorer.moveTo(Path.of("a"));
        explorer.moveTo(Path.of(".."));
        assertEquals(
            Path.of(""),
            Path.of(INITIAL_PATH).toAbsolutePath().relativize(explorer.getCurrentPath()));

        List<Path> inDirectory = List.of(
            Path.of("a"),
            Path.of("b"),
            Path.of("c")
        );
        assertEquals(inDirectory, explorer.getEverythingInDirectory());
    }

    @Test
    void moveTo_AbsolutePath_WorksCorrectly() throws IOException {
        explorer.moveTo(Path.of(INITIAL_PATH + "/a").toAbsolutePath());
        assertEquals(
            Path.of("a"),
            Path.of(INITIAL_PATH).toAbsolutePath().relativize(explorer.getCurrentPath()));

        List<Path> inDirectory = List.of(
            Path.of("file1.file"),
            Path.of("file2.file"),
            Path.of("file3.file")
        );

        assertEquals(inDirectory, explorer.getEverythingInDirectory());
    }

    @Test
    void createDirectory_InCurrentDirectory_WorksCorrectly() throws IOException {
        explorer.moveTo(Path.of("c"));
        explorer.createDir("testDir");
        assertTrue(Files.exists(Path.of(INITIAL_PATH + "/c/testDir")));
    }

    @Test
    void createDirectory_AbsolutePath_WorksCorrectly() throws IOException {
        explorer.createDir(Path.of(INITIAL_PATH + "/c/testDir").toAbsolutePath());
        assertTrue(Files.exists(Path.of(INITIAL_PATH + "/c/testDir")));
    }

    @Test
    void createDirectory_RelativePath_WorksCorrectly() throws IOException {
        explorer.createDir(Path.of(INITIAL_PATH + "/c/testDir"));
        assertTrue(Files.exists(Path.of(INITIAL_PATH + "/c/testDir")));
    }

    @Test
    void createFile_InCurrentDirectory_WorksCorrectly() throws IOException {
        explorer.moveTo(Path.of("c"));
        explorer.createFile("test.file");
        assertTrue(Files.exists(Path.of(INITIAL_PATH + "/c/test.file")));
    }

    @Test
    void createFile_AbsolutePath_WorksCorrectly() throws IOException {
        explorer.createFile(Path.of(INITIAL_PATH + "/c/test.file").toAbsolutePath());
        assertTrue(Files.exists(Path.of(INITIAL_PATH + "/c/test.file")));
    }

    @Test
    void createFile_RelativePath_WorksCorrectly() throws IOException {
        explorer.createFile(Path.of(INITIAL_PATH + "/c/test.file"));
        assertTrue(Files.exists(Path.of(INITIAL_PATH + "/c/test.file")));
    }

    @AfterEach
    void deleteDirectoryC() {
        try {
            deleteFile(new File(INITIAL_PATH + "/c"));
            Files.createDirectory(Path.of(INITIAL_PATH + "/c"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void deleteFile(File f) throws IOException {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFile(file);
                }
            }
        }
        Files.delete(f.toPath());
    }
}