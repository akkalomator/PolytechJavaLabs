package ru.petrov.lab3.commands.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceTest {

    @Test
    public void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");
        Replace command = new Replace(builder, 2, 4, "vwxyz");
        command.execute();
        StringBuilder control = new StringBuilder("abcdef");
        control.replace(2, 4, "vwxyz");
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    public void unExecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");
        Replace command = new Replace(builder, 2, 4, "vwxyz");
        command.execute();
        command.unExecute();
        assertEquals("abcdef", builder.toString());
    }

}