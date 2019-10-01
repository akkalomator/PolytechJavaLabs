package ru.petrov.lab3.commands.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteFromToTest {

    @Test
    void constructor_ThrowsOnStartIndexGreaterThanEndIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> new DeleteFromTo(new StringBuilder(), 4, 3));
    }

    @Test
    void execute_WorksCorrectly() {
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
    void unexecute_WorksCorrectly() {
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