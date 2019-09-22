package ru.petrov.lab3.commands.insert;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class InsertCharSequence extends ImprovedStringBuilderCommand {

    protected InsertCharSequence(StringBuilder builder, int dstOffset, CharSequence seq, int start, int end) {
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
