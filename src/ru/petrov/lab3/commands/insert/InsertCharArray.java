package ru.petrov.lab3.commands.insert;

public class InsertCharArray extends AbstractInsert {

    private final int index;
    private final char[] str;
    private final int offset;
    private final int len;

    public InsertCharArray(StringBuilder builder, int index, char[] str, int offset, int len) {
        super(builder, index, len);
        this.index = index;
        this.str = str;
        this.offset = offset;
        this.len = len;
    }

    @Override
    public void execute() {
        super.execute();

        builder.insert(index, str, offset, len);

        super.afterExecuted();
    }
}
