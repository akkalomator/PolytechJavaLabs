package ru.petrov.lab3.commands.append;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public abstract class AbstractAppend extends ImprovedStringBuilderCommand {

    private final int wordLength;

    AbstractAppend(StringBuilder builder, int wordLength) {
        super(builder);
        if (wordLength < 0) {
            throw new IllegalArgumentException("Word length must be greater than 0 or equal to it");
        }
        this.wordLength = wordLength;
    }

    @Override
    protected void unExecuteCommand() {
        builder.delete(builder.length() - wordLength, builder.length());
    }
}
