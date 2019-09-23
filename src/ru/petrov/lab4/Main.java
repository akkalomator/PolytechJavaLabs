package ru.petrov.lab4;

import ru.petrov.lab4.utils.Explorer;
import ru.petrov.lab4.utils.ExplorerProvider;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ExplorerProvider explorerProvider = new ExplorerProvider((new Explorer("")));
        ConsoleController app = new ConsoleController(System.in, System.out, explorerProvider);
        app.run();
    }
}
