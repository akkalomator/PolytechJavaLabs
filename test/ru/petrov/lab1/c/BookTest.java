package ru.petrov.lab1.c;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void constructor_ThrowsWhenAuthorIsNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "name", 2000));
        assertThrows(IllegalArgumentException.class, () -> new Book("", "name", 2000));
    }

    @Test
    public void constructor_ThrowsWhenNameIsNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Book("author", null, 2000));
        assertThrows(IllegalArgumentException.class, () -> new Book("author", "", 2000));
    }

    @Test
    public void setAuthor_ThrowsOnAuthorIsNullOrEmpty() {
        Book book = new Book("author", "name", 2000);
        assertThrows(IllegalArgumentException.class, () -> book.setAuthor(null));
        assertThrows(IllegalArgumentException.class, () -> book.setAuthor(""));
    }

    @Test
    public void setName_ThrowsOnNameIsNullOrEmpty() {
        Book book = new Book("author", "name", 2000);
        assertThrows(IllegalArgumentException.class, () -> book.setAuthor(null));
        assertThrows(IllegalArgumentException.class, () -> book.setAuthor(""));
    }
}