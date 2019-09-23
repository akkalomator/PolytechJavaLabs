package ru.petrov.lab4;

import ru.petrov.lab4.utils.ExplorerProvider;
import ru.petrov.lab4.utils.FileEditor;
import ru.petrov.lab4.utils.FileEditorProvider;
import ru.petrov.lab4.utils.io.Reader;
import ru.petrov.lab4.utils.SaveMode;
import ru.petrov.lab4.utils.io.Writer;

import java.io.IOException;

public class ConsoleController implements AutoCloseable {

    private static final String COMMAND_NOT_RECOGNIZED = "Cannot recognize command %s with %d parameters";
    private static final String WELCOME = "Welcome to File Explorer v0.0.1";
    private static final String HELP_COMMAND = "help";
    public static final String QUIT_COMMAND = "q";
    public static final String LIST_COMMAND = "ls";
    public static final String CHANGE_DIRECTORY_COMMAND = "cd";
    public static final String MAKE_DIRECTORY_COMMAND = "mkdir";
    public static final String CREATE_FILE_COMMAND = "touch";
    public static final String DELETE_COMMAND = "rm";
    private static final String OPEN_FILE_COMMAND = "open";
    private static final String INTERLINE_DELIMITER = "--------------------------------------------";
    private static final String OPTION_YES = "y";
    private static final String OPTION_NO = "n";
    private static final String OPTION_REWRITE = "r";
    private static final String OPTION_APPEND = "a";

    private final ExplorerProvider explorerProvider;
    private final Writer writer;
    private final Reader reader;

    public ConsoleController(Reader reader, Writer writer, ExplorerProvider explorerProvider) {
        this.reader = reader;
        this.writer = writer;
        this.explorerProvider = explorerProvider;
    }

    public void run() throws IOException {
        writer.writeString(WELCOME + '\n');
        while (true) {
            try {
                writer.writeString(getPrefix());
            } catch (IOException e) {
                writer.printError(e.getMessage());
            }
            String[] command = reader.getLine().trim().split(" ");
            if (command.length == 0) {
                continue;
            }
            if (!checkForValidParametersCount(command)) {
                continue;
            }
            switch (command[0]) {
                case HELP_COMMAND: {
                    writer.printHelp();
                    break;
                }
                case LIST_COMMAND: {
                    try {
                        explorerProvider.onListCommand(command[1]);
                    } catch (IOException e) {
                        writer.printError(e.getMessage());
                    }
                    break;
                }
                case CHANGE_DIRECTORY_COMMAND: {
                    try {
                        explorerProvider.onChangeDirectory(command[1]);
                    } catch (IllegalArgumentException | IOException e) {
                        writer.printError(e.getMessage());
                    }
                    break;
                }
                case MAKE_DIRECTORY_COMMAND: {
                    try {
                        explorerProvider.onMakeDirectory(command[1]);
                    } catch (IOException e) {
                        writer.printError(e.getMessage());
                    }
                    break;
                }
                case CREATE_FILE_COMMAND: {
                    try {
                        explorerProvider.onCreateFile(command[1]);
                    } catch (IOException e) {
                        writer.printError(e.getMessage());
                    }
                    break;
                }
                case DELETE_COMMAND: {
                    try {
                        explorerProvider.onDelete(command[1]);
                    } catch (IOException e) {
                        writer.printError(e.getMessage());
                    }
                    break;
                }
                case OPEN_FILE_COMMAND: {
                    FileEditor editor = new FileEditor(explorerProvider.getOpenedFilePath(command[1]));
                    try {
                        editorMenu(editor);
                    } catch (Exception e) {
                        writer.printError(e.getMessage());
                    }
                    break;
                }
                case QUIT_COMMAND: {
                    return;
                }
            }
        }
    }

    private boolean checkForValidParametersCount(String[] command) {
        switch (command[0]) {
            case HELP_COMMAND:
            case QUIT_COMMAND: {
                if (command.length != 1) {
                    writer.printError(String.format(COMMAND_NOT_RECOGNIZED, command[0], command.length));
                    return false;
                }
                break;
            }
            case LIST_COMMAND:
            case CHANGE_DIRECTORY_COMMAND:
            case MAKE_DIRECTORY_COMMAND:
            case CREATE_FILE_COMMAND:
            case DELETE_COMMAND:
            case OPEN_FILE_COMMAND: {
                if (command.length != 2) {
                    writer.printError(String.format(COMMAND_NOT_RECOGNIZED, command[0], command.length));
                    return false;
                }
                break;
            }
            default: {
                writer.printError("Cannot recognize command " + command[0]);
                return false;
            }
        }
        return true;
    }

    private String getPrefix() {
        return explorerProvider.getCurrentPath() + "> ";
    }

    private void editorMenu(FileEditor editor) throws Exception {
        writer.writeString("Editor\n");
        writer.writeString("File: " + editor.getName() + "\n");
        writer.writeString(INTERLINE_DELIMITER + "\n");

        editor.open();
        editor.readContent().forEach(line -> {
            try {
                writer.writeString(line + "\n");
            } catch (IOException e) {
                writer.printError("\n");
            }
        });
        writer.writeString(INTERLINE_DELIMITER + "\n");

        while (true) {
            String line = reader.getLine();
            if (line.equalsIgnoreCase(":" + QUIT_COMMAND)) {
                break;
            }
            editor.appendData(line);
        }

        String line = reader.getUserInputOutOfOptions(writer, "Save file?", OPTION_YES, OPTION_NO);
        if (line.equalsIgnoreCase(OPTION_YES)) {
            line = reader.getUserInputOutOfOptions(writer, "Rewrite file or append?", OPTION_REWRITE, OPTION_APPEND);
            if (line.equalsIgnoreCase(OPTION_REWRITE)) {
                editor.save(SaveMode.REWRITE);
            } else if (line.equalsIgnoreCase(OPTION_APPEND)) {
                editor.save(SaveMode.APPEND);
            } else {
                writer.printError("Internal error. Your files may not be saved");
            }
        }

        editor.close();
    }

    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
    }
}
