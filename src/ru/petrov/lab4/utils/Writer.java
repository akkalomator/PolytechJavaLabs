package ru.petrov.lab4.utils;

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

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
