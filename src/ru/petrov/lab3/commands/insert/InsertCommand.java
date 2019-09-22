package ru.petrov.lab3.commands.insert;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class InsertCommand<T> extends ImprovedStringBuilderCommand {

    protected InsertCommand(StringBuilder builder, int offset, T item) {
        super(builder);
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void unExecute() {
        super.unExecute();
    }
}
