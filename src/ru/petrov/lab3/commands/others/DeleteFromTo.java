package ru.petrov.lab3.commands.others;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class DeleteFromTo extends ImprovedStringBuilderCommand {

    private final int start;
    private final int end;
    private String deleted;

    public DeleteFromTo(StringBuilder builder, int start, int end) {
        super(builder);
        if (start > end) {
            throw new IndexOutOfBoundsException("Start position cannot be greater than end position");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    protected void executeCommand() {
        deleted = builder.substring(start, end);
        if (end - start > 1) {
            builder.delete(start, end);
        } else if (end - start == 1) {
            builder.deleteCharAt(start);
        }
    }

    @Override
    protected void unExecuteCommand() {
        builder.insert(start, deleted);
    }
}
