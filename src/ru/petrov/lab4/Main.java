package ru.petrov.lab4;

import ru.petrov.lab4.utils.Explorer;
import ru.petrov.lab4.utils.providers.ExplorerProvider;
import ru.petrov.lab4.utils.io.Reader;
import ru.petrov.lab4.utils.io.Writer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Reader reader = new Reader(System.in);
        Writer writer = new Writer(System.out);
        try {
            ExplorerProvider explorerProvider = new ExplorerProvider((new Explorer("")));
            Controller app = new Controller(reader, writer, explorerProvider);
            app.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
