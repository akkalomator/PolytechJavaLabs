package ru.petrov.lab3.commands.append;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractAppendTest {

    @Test
    void constructor_ThrowsOnWordLengthLessThanZero() {
        StringBuilder builder = new StringBuilder();
        assertThrows(IllegalArgumentException.class, () -> new AbstractAppend(builder, -1) {
            @Override
            protected void executeCommand() {
            }
        });
    }
}