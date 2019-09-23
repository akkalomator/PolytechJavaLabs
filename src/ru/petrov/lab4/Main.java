package ru.petrov.lab4;

import ru.petrov.lab4.utils.Explorer;
import ru.petrov.lab4.utils.ExplorerProvider;
import ru.petrov.lab4.utils.io.Reader;
import ru.petrov.lab4.utils.io.Writer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Reader reader = new Reader(System.in);
        Writer writer = new Writer(System.out);
        ExplorerProvider explorerProvider = new ExplorerProvider((new Explorer("")));
        ConsoleController app = new ConsoleController(reader, writer, explorerProvider);
        app.run();
    }
}
