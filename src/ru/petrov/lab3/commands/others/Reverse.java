package ru.petrov.lab3.commands.others;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class Reverse extends ImprovedStringBuilderCommand {

    public Reverse(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void executeCommand() {
        builder.reverse();
    }

    @Override
    protected void unExecuteCommand() {
        builder.reverse();
    }
}
