package ru.petrov.lab3.commands.append;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class AppendCodePoint extends ImprovedStringBuilderCommand {

    protected AppendCodePoint(StringBuilder builder, int codePoint) {
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
