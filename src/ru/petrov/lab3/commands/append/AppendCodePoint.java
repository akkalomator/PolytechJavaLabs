package ru.petrov.lab3.commands.append;

public class AppendCodePoint extends AbstractAppend {

    private final int codePoint;

    public AppendCodePoint(StringBuilder builder, int codePoint) {
        super(builder, 1);
        this.codePoint = codePoint;
    }

    @Override
    protected void executeCommand() {
        builder.appendCodePoint(codePoint);
    }
}
