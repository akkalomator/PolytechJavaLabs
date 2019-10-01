package ru.petrov.lab1.c;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BookShelfTest {

    private static List<Book> books;
    private static BookShelf shelf;

    @BeforeEach
    void createShelf() {
        books = List.of(
            new Book("author1", "name1", 2000),
            new Book("author1", "name2", 2050),
            new Book("author2", "name3", 2060),
            new Book("author2", "name1", 2070),
            new Book("author3", "name4", 2000),
            new Book("author3", "name4", 2000)
        );

        shelf = new BookShelf();
        books.forEach(
            book -> shelf.addBook(book)
        );
    }

    @Test
    void getAllBooks_WorksCorrectly() {
        assertEquals(
            books,
            shelf.getAllBooks()
        );
    }

    @Test
    void addBook_ThrowsWhenBookIsNull() {
        assertThrows(IllegalArgumentException.class, () -> shelf.addBook(null));
    }

    @Test
    void addBook_WorksCorrectly() {
        Book book = new Book("author4", "name5", 2001);
        shelf.addBook(book);
        assertTrue(shelf.getAllBooks().contains(book));
        assertEquals(6, book.getId());
    }

    @Test
    void getById_ThrowsOnNoSuchId() {
        assertThrows(IllegalArgumentException.class, () -> shelf.getById(999));
    }

    @Test
    void getById_WorksCorrectly() {
        assertEquals(books.get(0), shelf.getById(0));
    }

    @Test
    void getAllWhere_ReturnsEmptyListWhenNothingFound() {
        assertEquals(List.of(), shelf.getAllWhere((book -> book.getAuthor().equals("non existent"))));
    }

    @Test
    void getAllWhere_WorksCorrectly() {
        assertEquals(List.of(books.get(0), books.get(1)), shelf.getAllWhere(book -> book.getAuthor().equals("author1")));
        assertEquals(List.of(books.get(0), books.get(3)), shelf.getAllWhere(book -> book.getName().equals("name1")));
        assertEquals(List.of(books.get(0), books.get(4), books.get(5)), shelf.getAllWhere(book -> book.getYear() == 2000));
        assertEquals(List.of(books.get(4), books.get(5)), shelf.getAllWhere((book -> book.getAuthor().equals("author3"))));
    }

    @Test
    void updateAuthor_ThrowsOnNoSuchBook() {
        Book nonExistent = new Book("non existent", "non existent", 0);
        assertThrows(NoSuchElementException.class, () -> shelf.updateAuthor(nonExistent, "234"));
        assertThrows(NoSuchElementException.class, () -> shelf.updateAuthor(999, "234"));
    }

    @Test
    void updateAuthor_WorksCorrectly() {
        String newAuthor1 = "newAuthor1";
        Book bookToChange1 = books.get(0);
        shelf.updateAuthor(bookToChange1, newAuthor1);
        assertEquals(newAuthor1, bookToChange1.getAuthor());

        String newAuthor2 = "newAuthor2";
        Book bookToChange2 = books.get(1);
        shelf.updateAuthor(1, newAuthor2);
        assertEquals(newAuthor2, bookToChange2.getAuthor());
    }

    @Test
    void updateName_ThrowsOnNoSuchBook() {
        Book nonExistent = new Book("non existent", "non existent", 0);
        assertThrows(NoSuchElementException.class, () -> shelf.updateName(nonExistent, "234"));
        assertThrows(NoSuchElementException.class, () -> shelf.updateAuthor(999, "234"));
    }

    @Test
    void updateName_WorksCorrectly() {
        String newName1 = "newName1";
        Book bookToChange1 = books.get(0);
        shelf.updateName(bookToChange1, newName1);
        assertEquals(newName1, bookToChange1.getName());

        String newName2 = "newName2";
        Book bookToChange2 = books.get(1);
        shelf.updateName(1, newName2);
        assertEquals(newName2, bookToChange2.getName());
    }

    @Test
    void updateYear_ThrowsOnNoSuchBook() {
        Book nonExistent = new Book("non existent", "non existent", 0);
        assertThrows(NoSuchElementException.class, () -> shelf.updateYear(nonExistent, 0));
        assertThrows(NoSuchElementException.class, () -> shelf.updateYear(999, 0));
    }

    @Test
    void updateYear_WorksCorrectly() {
        int newYear1 = 2001;
        Book bookToChange1 = books.get(0);
        shelf.updateYear(bookToChange1, newYear1);
        assertEquals(newYear1, bookToChange1.getYear());

        int newYear2 = 2002;
        Book bookToChange2 = books.get(1);
        shelf.updateYear(1, newYear2);
        assertEquals(newYear2, bookToChange2.getYear());
    }

    @Test
    void deleteAllBooks_WorksCorrectly() {
        shelf.deleteAllBooks();
        assertEquals(List.of(), shelf.getAllBooks());
    }

    @Test
    void deleteBook_ThrowsOnNoSuchBookFound() {
        Book nonExistent = new Book("non existent", "non existent", 0);
        assertThrows(NoSuchElementException.class, () -> shelf.deleteBook(nonExistent));
    }

    @Test
    void deleteBook_WorksCorrectly() {
        Book bookToDelete1 = books.get(0);
        shelf.deleteBook(bookToDelete1);
        assertFalse(shelf.getAllBooks().contains(bookToDelete1));

        Book bookToDelete2 = books.get(5);
        shelf.deleteBook(bookToDelete2);
        assertTrue(shelf.getAllBooks().contains(bookToDelete2));
    }

    @Test
    void deleteById_ThrowsOnNoSuchBookFound() {
        assertThrows(NoSuchElementException.class, () -> shelf.deleteById(999));
    }

    @Test
    void deleteById_WorksCorrectly() {
        int id1 = 0;
        Book bookToDelete1 = books.get(id1);
        shelf.deleteById(id1);
        assertFalse(shelf.getAllBooks().contains(bookToDelete1));

        int id2 = 5;
        Book bookToDelete2 = books.get(id2);
        shelf.deleteBook(bookToDelete2);
        assertTrue(shelf.getAllBooks().contains(bookToDelete2));
    }

    @Test
    void deleteAllWhere_doesNothingWhenPredicateDontMatch() {
        shelf.deleteAllWhere(book -> book.getAuthor().equals("non existent"));
        assertEquals(books, shelf.getAllBooks());
    }

    @Test
    void deleteAllWhere_WorksCorrectly() {
        shelf.deleteAllWhere(book -> book.getAuthor().equals("author1"));
        assertEquals(
            List.of(
                books.get(2),
                books.get(3),
                books.get(4),
                books.get(5)
            ),
            shelf.getAllBooks()
        );
    }
}