package ru.petrov.lab3.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImprovedStringBuilderCommandTest {

    private static class ImprovedStringBuilderCommandImpl extends ImprovedStringBuilderCommand {

        ImprovedStringBuilderCommandImpl(StringBuilder builder) {
            super(builder);
        }

        @Override
        protected void executeCommand() {
        }

        @Override
        protected void unExecuteCommand() {
        }
    }

    @Test
    void constructor_ThrowsOnStringBuilderIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new ImprovedStringBuilderCommandImpl(null));
    }

    @Test
    void execute_ThrowsWhenAlreadyBeenExecuted() {
        StringBuilder builder = new StringBuilder("abc");
        ImprovedStringBuilderCommand command = new ImprovedStringBuilderCommandImpl(builder);
        command.execute();
        assertThrows(IllegalStateException.class, command::execute);
    }


    @Test
    void unExecute_ThrowsWhenHasNotBeenYetExecuted() {
        StringBuilder builder = new StringBuilder("abc");
        ImprovedStringBuilderCommand command = new ImprovedStringBuilderCommandImpl(builder);
        assertThrows(IllegalStateException.class, command::unExecute);
    }

    @Test
    public void unExecute_ThrowsWhenAlreadyBeenUnexecuted() {
        StringBuilder builder = new StringBuilder("abc");
        ImprovedStringBuilderCommand command = new ImprovedStringBuilderCommandImpl(builder);
        command.execute();
        command.unExecute();
        assertThrows(IllegalStateException.class, command::unExecute);
    }
}