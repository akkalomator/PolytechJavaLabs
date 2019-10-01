package ru.petrov.lab3.commands.insert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertCharArrayTest {

    @Test
    void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        int index = 5;
        char[] str = {'q', 'w', 'e', 'r', 't', 'y'};
        int offset = 2;
        int len = 3;
        InsertCharArray command = new InsertCharArray(builder, index, str, offset, len);
        command.execute();
        StringBuilder control = new StringBuilder("abcdefghijklm").insert(index, str, offset, len);
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        int index = 5;
        char[] str = {'q', 'w', 'e', 'r', 't', 'y'};
        int offset = 2;
        int len = 3;
        InsertCharArray command = new InsertCharArray(builder, index, str, offset, len);
        command.execute();
        command.unExecute();
        assertEquals("abcdefghijklm", builder.toString());
    }
}