package ru.petrov.lab4.utils;

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

    @Override
    public void close() {
        sc.close();
    }
}
