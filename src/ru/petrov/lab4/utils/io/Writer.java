package ru.petrov.lab4.utils.io;

import ru.petrov.lab4.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Writer implements AutoCloseable {
    private final OutputStreamWriter writer;

    public Writer(OutputStream stream) {
        this.writer = new OutputStreamWriter(stream);
    }

    public void writeString(String s) throws IOException {
        writer.write(s);
        writer.flush();
    }

    public void printError(String error) {
        try {
            writeString("ERROR: " + error + " \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printHelp() {
        try {
            writeString(
                "Available commands: \n" +
                    Controller.LIST_COMMAND + " <path> - lists all files and directories in current directory\n" +
                    Controller.CHANGE_DIRECTORY_COMMAND + " <path> - move to specified path\n" +
                    Controller.MAKE_DIRECTORY_COMMAND + " <name> - create directory\n" +
                    Controller.CREATE_FILE_COMMAND + " <name> - create file\n" +
                    Controller.OPEN_FILE_COMMAND + " <name> - opens file for editing" +
                    Controller.DELETE_COMMAND + " <name> - delete file or directory\n" +
                    Controller.QUIT_COMMAND + " - quit from program\n"
            );
        } catch (IOException e) {
            printError(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
