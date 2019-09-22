package ru.petrov.lab3.commands.insert;

import java.lang.reflect.Type;

public class InsertCommand<T> extends AbstractInsert {

    private final int offset;
    private final T item;
    private final Type type;

    public InsertCommand(StringBuilder builder, int offset, T item) {
        super(builder, offset, item.toString().length());
        this.offset = offset;
        this.item = item;
        this.type = item.getClass();
    }

    @Override
    public void execute() {
        super.execute();

        if (type == Boolean.class) {
            builder.insert(offset, (boolean) item);
        } else if (type == Character.class) {
            builder.insert(offset, (char) item);
        } else if (type == Double.class) {
            builder.insert(offset, (double) item);
        } else if (type == Float.class) {
            builder.insert(offset, (float) item);
        } else if (type == Integer.class) {
            builder.insert(offset, (int) item);
        } else if (type == Long.class) {
            builder.insert(offset, (long) item);
        } else if (type == String.class) {
            builder.insert(offset, (String) item);
        } else if (type == StringBuffer.class) {
            builder.insert(offset, (StringBuffer) item);
        } else {
            builder.insert(offset, item);
        }

        super.afterExecuted();
    }
}
