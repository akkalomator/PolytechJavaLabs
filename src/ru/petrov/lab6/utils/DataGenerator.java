package ru.petrov.lab6.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

    private static final int TRANSACTION_NUMBER = 5000;
    private static final int USERS_NUMBER = 10;
    private static final int MAX_SUM = 50000;

    public static void main(String[] args) throws IOException {
        Random r = new Random();
        FileWriter writer = new FileWriter("resources/lab6/data.xml");
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<transactions count=\"" + TRANSACTION_NUMBER +"\">\n"
        );

        for (int i = 0; i < TRANSACTION_NUMBER; i++) {
            int from;
            int to;
            do {
                from = r.nextInt(USERS_NUMBER);
                to = r.nextInt(USERS_NUMBER);
            } while (from == to);
            writer.write(String.format(
                "\t<transaction id=\"%d\" from=\"%d\" to=\"%d\" amount=\"%d\" />\n",
                i,
                from,
                to,
                r.nextInt(MAX_SUM)
            ));
        }

        writer.write("</transactions>");
        writer.close();
    }
}
