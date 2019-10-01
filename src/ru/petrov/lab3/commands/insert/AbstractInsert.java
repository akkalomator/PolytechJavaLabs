package ru.petrov.lab3.commands.insert;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public abstract class AbstractInsert extends ImprovedStringBuilderCommand {

    private final int from;
    private final int length;

    AbstractInsert(StringBuilder builder, int from, int length) {
        super(builder);
        if (length < 0) {
            throw new IllegalArgumentException("Insert word length must be greater than zero or equal to it");
        }
        this.from = from;
        this.length = length;
    }

    @Override
    protected void unExecuteCommand() {
        builder.delete(from, from + length);
    }
}
