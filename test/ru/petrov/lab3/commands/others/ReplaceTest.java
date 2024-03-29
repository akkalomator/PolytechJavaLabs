package ru.petrov.lab3.commands.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceTest {

    @Test
    void constructor_ThrowsOnEndIndexLessThanStartIndex() {
        StringBuilder builder = new StringBuilder();
        assertThrows(IllegalArgumentException.class, () -> new Replace(builder, 5, 3, "sdf"));
    }

    @Test
    void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");
        Replace command = new Replace(builder, 2, 4, "vwxyz");
        command.execute();
        StringBuilder control = new StringBuilder("abcdef");
        control.replace(2, 4, "vwxyz");
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    void unExecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");
        Replace command = new Replace(builder, 2, 4, "vwxyz");
        command.execute();
        command.unExecute();
        assertEquals("abcdef", builder.toString());
    }
}