package ru.petrov.lab3.commands.append;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppendCharArrayTest {

    @Test
    public void execute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        char[] str = {'q', 'w', 'e', 'r', 't', 'y'};
        int offset = 2;
        int len = 3;
        AppendCharArray command = new AppendCharArray(builder, str, offset, len);
        command.execute();
        StringBuilder control = new StringBuilder("abcdefghijklm").append(str, offset, len);
        assertEquals(control.toString(), builder.toString());
    }

    @Test
    public void unexecute_WorksCorrectly() {
        StringBuilder builder = new StringBuilder("abcdefghijklm");
        char[] str = {'q', 'w', 'e', 'r', 't', 'y'};
        int offset = 2;
        int len = 3;
        AppendCharArray command = new AppendCharArray(builder, str, offset, len);
        command.execute();
        command.unExecute();
        assertEquals("abcdefghijklm", builder.toString());
    }
}