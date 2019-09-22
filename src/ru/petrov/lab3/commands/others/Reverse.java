package ru.petrov.lab3.commands.others;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class Reverse extends ImprovedStringBuilderCommand {

    public Reverse(StringBuilder builder) {
        super(builder);
    }

    @Override
    public void execute() {
        super.execute();
        builder.reverse();
        super.afterExecuted();
    }

    @Override
    public void unExecute() {
        super.unExecute();
        builder.reverse();
        super.afterUnexecuted();
    }
}
