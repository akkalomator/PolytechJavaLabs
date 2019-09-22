package ru.petrov.lab3.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImprovedStringBuilderCommandTest {

    private class ImprovedStringBuilderCommandImpl extends ImprovedStringBuilderCommand {

        protected ImprovedStringBuilderCommandImpl(StringBuilder builder) {
            super(builder);
        }

        @Override
        public void execute() {
            super.execute();
            super.afterExecuted();
        }

        @Override
        public void unExecute() {
            super.unExecute();
            super.afterUnexecuted();
        }
    }

    @Test
    public void constructor_ThrowsOnStringBuilderIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new ImprovedStringBuilderCommandImpl(null));
    }

    @Test
    public void execute_ThrowsWhenAlreadyBeenExecuted() {

        StringBuilder builder = new StringBuilder("abc");
        ImprovedStringBuilderCommand command = new ImprovedStringBuilderCommandImpl(builder);
        command.execute();
        assertThrows(IllegalStateException.class, command::execute);
    }


    @Test
    public void unExecute_ThrowsWhenHasNotBeenYetExecuted() {

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