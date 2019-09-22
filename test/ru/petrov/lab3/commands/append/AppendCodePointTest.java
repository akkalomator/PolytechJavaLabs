package ru.petrov.lab3.commands.append;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppendCodePointTest {

    @Test
    public void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        AppendCodePoint command = new AppendCodePoint(builder, 65);
        command.execute();
        StringBuilder control = new StringBuilder("abcdefghijklm").appendCodePoint(65);
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    public void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        AppendCodePoint command = new AppendCodePoint(builder, 65);
        command.execute();
        command.unExecute();
        assertEquals("abcdefghijklm", builder.toString());
    }

}