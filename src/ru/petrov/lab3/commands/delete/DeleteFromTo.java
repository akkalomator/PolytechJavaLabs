package ru.petrov.lab3.commands.delete;

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
    public void execute() {
        super.execute();

        deleted = builder.substring(start, end);
        if (end - start > 1) {
            builder.delete(start, end);
        } else if (end - start == 1) {
            builder.deleteCharAt(start);
        }

        super.afterExecuted();
    }

    @Override
    public void unExecute() {
        super.unExecute();

        builder.insert(start, deleted);

        super.afterUnexecuted();
    }
}
