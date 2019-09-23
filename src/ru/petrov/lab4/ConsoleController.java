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

    private static Explorer explorer;
    private static Scanner sc;

    public static void run() {
        System.out.println("Welcome to File Explorer v0.0.1");

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
                case "help": {
                    printHelp();
                    break;
                }
                case "ls": {
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
                case "cd": {
                    try {
                        Path path = Path.of(command[1]);
                        explorer.moveTo(path);
                    } catch (IllegalArgumentException | IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case "mkdir": {
                    try {
                        explorer.createDir(command[1]);
                    } catch (IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case "touch": {
                    try {
                        explorer.createFile(command[1]);
                    } catch (IOException e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case "rm": {
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
                case "open": {
                    FileEditor editor = new FileEditor(explorer.getCurrentPath().resolve(command[1]));
                    try {
                        editorMenu(editor);
                    } catch (Exception e) {
                        printError(e.getMessage());
                    }
                    break;
                }
                case "q": {
                    return;
                }
            }
        }
    }

    private static boolean checkForValidParametersCount(String[] command) {
        switch (command[0]) {
            case "help":
            case "q": {
                if (command.length != 1) {
                    printError("Cannot recognize command " + command[0] + " with " + command.length + " parameters");
                    return false;
                }
                break;
            }
            case "ls":
            case "cd":
            case "mkdir":
            case "touch":
            case "rm":
            case "open": {
                if (command.length != 2) {
                    printError("Cannot recognize command " + command[0] + " with " + command.length + " parameters");
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
                "ls <path> - lists all files and directories in current directory\n" +
                "cd <path> - move to specified path\n" +
                "mkdir <name> - create directory\n" +
                "touch <name> - create file\n" +
                "rm <name> - delete file or directory\n" +
                "q - quit from program"
        );
    }

    private static void printError(String error) {
        System.out.println("ERROR: " + error);
    }


    private static void editorMenu(FileEditor editor) throws Exception {
        System.out.println("Editor");
        System.out.println("File: " + editor.getName());
        System.out.println("--------------------------------------------");
        System.out.println("Content:\n");

        editor.open();
        editor.readContent().forEach(System.out::println);
        System.out.println("--------------------------------------------");

        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase(":q")) {
                break;
            }
            editor.appendData(line);
        }

        while (true) {
            System.out.println("Save? (y/n)");
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("y")) {
                while (true) {
                    System.out.println("Rewrite file or append? (r/a)");
                    line = sc.nextLine();
                    if (line.equalsIgnoreCase("r")) {
                        editor.save(SaveMode.REWRITE);
                        break;
                    }
                    if (line.equalsIgnoreCase("a")) {
                        editor.save(SaveMode.APPEND);
                        break;
                    }
                }
                break;
            }
            if (line.equalsIgnoreCase("n")) {
                break;
            }
        }

        editor.close();
    }
}
