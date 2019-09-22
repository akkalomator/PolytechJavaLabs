package ru.petrov.lab3.commands.append;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class AppendCharArray extends ImprovedStringBuilderCommand {

    public AppendCharArray(StringBuilder builder, char[] str, int offset, int len) {
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
