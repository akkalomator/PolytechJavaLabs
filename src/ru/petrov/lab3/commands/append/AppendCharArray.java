package ru.petrov.lab3.commands.append;

public class AppendCharArray extends AbstractAppend {

    private final char[] str;
    private final int offset;
    private final int len;

    public AppendCharArray(StringBuilder builder, char[] str, int offset, int len) {
        super(builder, len);
        this.str = str;
        this.offset = offset;
        this.len = len;
    }

    @Override
    protected void executeCommand() {
        builder.append(str, offset, len);
    }
}
