package ru.petrov.lab3.commands.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTest {

    @Test
    public void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");
        Reverse command = new Reverse(builder);
        command.execute();
        assertEquals("fedcba", builder.toString());
    }

    @Test
    public void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdef");
        Reverse command = new Reverse(builder);
        command.execute();
        command.unExecute();
        assertEquals("abcdef", builder.toString());
    }

}