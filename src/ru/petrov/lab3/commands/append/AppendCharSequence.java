package ru.petrov.lab3.commands.append;

public class AppendCharSequence extends AbstractAppend {

    private final CharSequence seq;
    private final int start;
    private final int end;

    public AppendCharSequence(StringBuilder builder, CharSequence seq, int start, int end) {
        super(builder, end - start);
        this.seq = seq;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        super.execute();

        builder.append(seq, start, end);

        super.afterExecuted();
    }
}
