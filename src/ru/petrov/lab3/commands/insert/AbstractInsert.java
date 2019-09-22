package ru.petrov.lab3.commands.insert;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public abstract class AbstractInsert extends ImprovedStringBuilderCommand {

    private final int from;
    private final int lenhth;

    protected AbstractInsert(StringBuilder builder, int from, int length) {
        super(builder);
        this.from = from;
        this.lenhth = length;
    }

    @Override
    public void unExecute() {
        super.unExecute();

        builder.delete(from, from + lenhth);

        super.afterUnexecuted();
    }
}
