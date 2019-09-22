package ru.petrov.lab3.commands.insert;

public class InsertCharSequence extends AbstractInsert {

    private final int dstOffset;
    private final CharSequence seq;
    private final int start;
    private final int end;

    public InsertCharSequence(StringBuilder builder, int dstOffset, CharSequence seq, int start, int end) {
        super(builder, dstOffset, end - start);
        this.dstOffset = dstOffset;
        this.seq = seq;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        super.execute();

        builder.insert(dstOffset, seq, start, end);

        super.afterExecuted();
    }
}
