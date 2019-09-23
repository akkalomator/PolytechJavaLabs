package ru.petrov.lab4;

import ru.petrov.lab4.utils.Explorer;
import ru.petrov.lab4.utils.ExplorerProvider;
import ru.petrov.lab4.utils.Writer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Writer writer = new Writer(System.out);
        ExplorerProvider explorerProvider = new ExplorerProvider((new Explorer("")));
        ConsoleController app = new ConsoleController(System.in, writer, explorerProvider);
        app.run();
    }
}
