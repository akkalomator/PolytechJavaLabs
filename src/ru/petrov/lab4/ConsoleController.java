package ru.petrov.lab4;

import ru.petrov.lab4.utils.Explorer;
import ru.petrov.lab4.utils.FileEditor;
import ru.petrov.lab4.utils.SaveMode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    private static final String COMMAND_NOT_RECOGNIZED = "Cannot recognize command %s with %d parameters";
    private static final String WELCOME = "Welcome to File Explorer v0.0.1";
    private static final String HELP_COMMAND = "help";
    private static final String QUIT_COMMAND = "q";
    private static final String LIST_COMMAND = "ls";
    private static final String CHANGE_DIRECTORY_COMMAND = "cd";
    private static final String MAKE_DIRECTORY_COMMAND = "mkdir";
    private static final String CREATE_FILE_COMMAND = "touch";
    private static final String DELETE_COMMAND = "rm";
    private static final String OPEN_FILE_COMMAND = "open";
    private static final String INTERLINE_DELIMITER = "--------------------------------------------";
    private static final String OPTION_YES = "y";
    private static final String OPTION_NO = "n";
    private static final String OPTION_REWRITE = "r";
    private static final String OPTION_APPEND = "a";

    private static Explorer explorer;
    private static Scanner sc;

    public static void run() {
        System.out.println(WELCOME);

        sc = new Scanner(System.in);
        try {
            explorer = new Explorer("");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            System.out.print(getPrefix());
            String[] command = sc.nextLine().trim().split(" ");
            if (command.length == 0) {
                continue;
            }
            if (!checkForValidParametersCount(command)) {
                continue;
            }
            switch (command[0]) {
                case HELP_COMMAND: {
                    printHelp();
                    break;
                }
                case LIST_COMMAND: {
                    try {
                        List<Path> content;
                        if (command[1].equals(".")) {
                            content = explorer.getEverythingInDirectory();
                        } else {
                            content = explorer.getEverythingInDirectory(command[1]);
                        }
                        content.forEach(System.out::println);
                    } catch (IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case CHANGE_DIRECTORY_COMMAND: {
                    try {
                        Path path = Path.of(command[1]);
                        explorer.moveTo(path);
                    } catch (IllegalArgumentException | IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case MAKE_DIRECTORY_COMMAND: {
                    try {
                        explorer.createDir(command[1]);
                    } catch (IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case CREATE_FILE_COMMAND: {
                    try {
                        explorer.createFile(command[1]);
                    } catch (IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case DELETE_COMMAND: {
                    try {
                        Path path = Path.of(command[1]);
                        if (!path.isAbsolute()) {
                            path = explorer.getCurrentPath().resolve(path);
                        }
                        explorer.delete(new File(path.toString()));
                    } catch (IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case OPEN_FILE_COMMAND: {
                    FileEditor editor = new FileEditor(explorer.getCurrentPath().resolve(command[1]));
                    try {
                        editorMenu(editor);
                    } catch (Exception e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case QUIT_COMMAND: {
                    return;
                }
            }
        }
    }

    private static boolean checkForValidParametersCount(String[] command) {
        switch (command[0]) {
            case HELP_COMMAND:
            case QUIT_COMMAND: {
                if (command.length != 1) {
                    printError(String.format(COMMAND_NOT_RECOGNIZED, command[0], command.length));
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
                    printError(String.format(COMMAND_NOT_RECOGNIZED, command[0], command.length));
                    return false;
                }
                break;
            }
            default: {
                printError("Cannot recognize command " + command[0]);
                return false;
            }
        }
        return true;
    }

    private static String getPrefix() {
        return explorer.getCurrentPath() + "> ";
    }

    private static void printHelp() {
        System.out.println(
            "Available commands: \n" +
                LIST_COMMAND + " <path> - lists all files and directories in current directory\n" +
                CHANGE_DIRECTORY_COMMAND + " <path> - move to specified path\n" +
                MAKE_DIRECTORY_COMMAND + " <name> - create directory\n" +
                CREATE_FILE_COMMAND + " <name> - create file\n" +
                DELETE_COMMAND + " <name> - delete file or directory\n" +
                QUIT_COMMAND + " - quit from program"
        );
    }

    private static void printError(String error) {
        System.out.println("ERROR: " + error);
    }


    private static void editorMenu(FileEditor editor) throws Exception {
        System.out.println("Editor");
        System.out.println("File: " + editor.getName());
        System.out.println(INTERLINE_DELIMITER);

        editor.open();
        editor.readContent().forEach(System.out::println);
        System.out.println(INTERLINE_DELIMITER);

        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase(":" + QUIT_COMMAND)) {
                break;
            }
            editor.appendData(line);
        }

        String line = getUserInputOutOfOptions("Save file?", OPTION_YES, OPTION_NO);
        if (line.equalsIgnoreCase(OPTION_YES)) {
            line = getUserInputOutOfOptions("Rewrite file or append?", OPTION_REWRITE, OPTION_APPEND);
            if (line.equalsIgnoreCase(OPTION_REWRITE)) {
                editor.save(SaveMode.REWRITE);
            } else if (line.equalsIgnoreCase(OPTION_APPEND)) {
                editor.save(SaveMode.APPEND);
            } else {
                printError("Internal error. Your files may not be saved");
            }
        }

        editor.close();
    }

    private static String getUserInputOutOfOptions(String message, String... options) {
        StringBuilder builder = new StringBuilder(message);
        builder.append('(');
        for (String option : options) {
            builder.append(option).append('/');
        }
        builder.deleteCharAt(builder.length() - 1)
            .append("):");
        message = builder.toString();
        while (true) {
            System.out.println(message);
            String line = sc.nextLine();
            for (String option : options) {
                if (line.equalsIgnoreCase(option)) {
                    return line;
                }
            }
        }
    }
}
