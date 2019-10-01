package ru.petrov.lab3.commands.append;

import java.lang.reflect.Type;

public class Append<T> extends AbstractAppend {

    private final T item;
    private final Type type;

    public Append(StringBuilder builder, T item) {
        super(builder, item.toString().length());

        this.item = item;
        this.type = item.getClass();
    }

    @Override
    protected void executeCommand() {
        if (type == Boolean.class) {
            builder.append((boolean) item);
        } else if (type == Character.class) {
            builder.append((char) item);
        } else if (type == Double.class) {
            builder.append((double) item);
        } else if (type == Float.class) {
            builder.append((float) item);
        } else if (type == Integer.class) {
            builder.append((int) item);
        } else if (type == Long.class) {
            builder.append((long) item);
        } else if (type == String.class) {
            builder.append((String) item);
        } else if (type == StringBuffer.class) {
            builder.append((StringBuffer) item);
        } else {
            builder.append(item);
        }
    }
}
