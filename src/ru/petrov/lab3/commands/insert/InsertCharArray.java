package ru.petrov.lab3.commands.insert;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class InsertCharArray extends ImprovedStringBuilderCommand {

    protected InsertCharArray(StringBuilder builder, int index, char[] str, int offset, int len) {
        super(builder);
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void unExecute() {
        super.execute();
    }
}
