package ru.petrov.lab3.commands.insert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractInsertTest {

    @Test
    public void constructor_ThrowsOnLengthLessThanZero() {
        StringBuilder builder = new StringBuilder();
        assertThrows(IllegalArgumentException.class, ()-> new AbstractInsert(builder, 5, -1) {});
    }

}