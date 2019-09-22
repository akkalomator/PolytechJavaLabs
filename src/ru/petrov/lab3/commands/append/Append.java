package ru.petrov.lab3.commands.append;

import java.lang.reflect.Type;

public class Append<T> extends AbstractAppend {

    protected Append(StringBuilder builder, T item) {
        super(builder, item.toString().length());
    }

    @Override
    public void execute() {
        super.execute();
    }
}
