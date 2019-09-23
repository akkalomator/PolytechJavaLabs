package ru.petrov.lab4.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Reader implements AutoCloseable {

    private final Scanner sc;

    public Reader(InputStream inputStream) {
        this.sc = new Scanner(inputStream);
    }

    public String getLine() {
        return sc.nextLine();
    }

    public String getUserInputOutOfOptions(Writer writer, String message, String... options) {
        StringBuilder builder = new StringBuilder(message);
        builder.append('(');
        for (String option : options) {
            builder.append(option).append('/');
        }
        builder.deleteCharAt(builder.length() - 1)
            .append("):");
        message = builder.toString();
        while (true) {
            try {
                writer.writeString(message + "\n");
            } catch (IOException e) {
                writer.printError(e.getMessage());
            }
            String line = getLine();
            for (String option : options) {
                if (line.equalsIgnoreCase(option)) {
                    return line;
                }
            }
        }
    }

    @Override
    public void close() {
        sc.close();
    }
}
