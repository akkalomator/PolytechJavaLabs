package ru.petrov.lab3.commands.append;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class AppendCharSequence extends ImprovedStringBuilderCommand {

    public AppendCharSequence(StringBuilder builder, CharSequence seq, int start, int end) {
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
