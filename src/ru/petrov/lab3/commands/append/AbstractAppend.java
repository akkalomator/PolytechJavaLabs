package ru.petrov.lab3.commands.append;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public abstract class AbstractAppend extends ImprovedStringBuilderCommand {

    protected AbstractAppend(StringBuilder builder, int wordLength) {
        super(builder);
    }

    @Override
    public void unExecute() {
        super.unExecute();
    }
}
