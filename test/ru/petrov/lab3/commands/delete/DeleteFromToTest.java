package ru.petrov.lab3.commands.delete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFromToTest {

    @Test
    public void constructor_ThrowsOnStartIndexGreaterThanEndIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> new DeleteFromTo(new StringBuilder(), 4, 3));
    }

    @Test
    public void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefgh");
        DeleteFromTo command = new DeleteFromTo(builder, 2, 5);
        command.execute();
        StringBuilder control = new StringBuilder("abcdefgh");
        control.delete(2, 5);
        assertEquals(control.toString(), builder.toString());

        command = new DeleteFromTo(builder, 2, 3);
        command.execute();
        control.delete(2, 3);
        assertEquals(control.toString(), builder.toString());

        command = new DeleteFromTo(builder, 3, 3);
        command.execute();
        control.delete(3, 3);
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    public void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefgh");
        DeleteFromTo command = new DeleteFromTo(builder, 2, 5);
        command.execute();
        command.unExecute();
        assertEquals("abcdefgh", builder.toString());

        command = new DeleteFromTo(builder, 2, 3);
        command.execute();
        command.unExecute();
        assertEquals("abcdefgh", builder.toString());

        command = new DeleteFromTo(builder, 3, 3);
        command.execute();
        command.unExecute();
        assertEquals("abcdefgh", builder.toString());
    }
}