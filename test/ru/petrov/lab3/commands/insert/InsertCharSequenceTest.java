package ru.petrov.lab3.commands.insert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertCharSequenceTest {


    @Test
    public void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        int offset = 3;
        CharSequence str = "qwerty";
        int start = 2;
        int end = 3;
        InsertCharSequence command = new InsertCharSequence(builder, offset, str, start, end);
        command.execute();
        StringBuilder control = new StringBuilder("abcdefghijklm").insert(offset, str, start, end);
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    public void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        CharSequence str = "qwerty";
        int offset = 3;
        int start = 2;
        int end = 3;
        InsertCharSequence command = new InsertCharSequence(builder, offset, str, start, end);
        command.execute();
        command.unExecute();
        assertEquals("abcdefghijklm", builder.toString());
    }

}