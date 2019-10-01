package ru.petrov.lab3.commands;

public abstract class ImprovedStringBuilderCommand {

    protected StringBuilder builder;
    private boolean isExecuted;

    protected ImprovedStringBuilderCommand(StringBuilder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("String builder cannot be null");
        }
        this.builder = builder;
        isExecuted = false;
    }

    public void execute() {
        if (isExecuted) {
            throw new IllegalStateException("Command has already been executed");
        }
        executeCommand();
        isExecuted = true;
    }

    protected abstract void executeCommand();

    public void unExecute() {
        if (!isExecuted) {
            throw new IllegalStateException("Command has not been executed yet or has already been unexecuted");
        }
        unExecuteCommand();
        isExecuted = false;
    }

    protected abstract void unExecuteCommand();
}

