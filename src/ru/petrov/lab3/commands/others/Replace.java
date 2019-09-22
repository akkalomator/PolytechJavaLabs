package ru.petrov.lab3.commands.others;

import ru.petrov.lab3.commands.ImprovedStringBuilderCommand;

public class Replace extends ImprovedStringBuilderCommand {

    private final int start;
    private final int end;
    private final String str;
    private String replaced;

    public Replace(StringBuilder builder, int start, int end, String str) {
        super(builder);
        if (end < start) {
            throw new IllegalArgumentException("End index cannot be less than start index");
        }
        this.start = start;
        this.end = end;
        this.str = str;
    }

    @Override
    public void execute() {
        super.execute();

        replaced = builder.substring(start, end);
        builder.replace(start, end, str);

        super.afterExecuted();
    }

    @Override
    public void unExecute() {
        super.unExecute();
        builder.replace(start, start + str.length(), replaced);
        super.afterUnexecuted();
    }
}
