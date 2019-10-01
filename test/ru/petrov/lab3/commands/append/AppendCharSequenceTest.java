package ru.petrov.lab3.commands.append;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppendCharSequenceTest {

    @Test
    void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        CharSequence str = "qwerty";
        int start = 2;
        int end = 3;
        AppendCharSequence command = new AppendCharSequence(builder, str, start, end);
        command.execute();
        StringBuilder control = new StringBuilder("abcdefghijklm").append(str, start, end);
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        CharSequence str = "qwerty";
        int start = 2;
        int end = 3;
        AppendCharSequence command = new AppendCharSequence(builder, str, start, end);
        command.execute();
        command.unExecute();
        assertEquals("abcdefghijklm", builder.toString());
    }
}