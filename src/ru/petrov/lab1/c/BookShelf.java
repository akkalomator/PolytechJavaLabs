package ru.petrov.lab1.c;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookShelf {

    private Map<Integer, Book> books;
    private int lastId;

    public BookShelf() {
        this.books = new HashMap<>();
        lastId = -1;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        lastId++;
        book.setId(lastId);
        books.put(lastId, book);
    }

    public void addBook(String author, String name, int year) {
        addBook(new Book(author, name, year));
    }

    public List<Book> getAllBooks() {
        return List.copyOf(books.values());
    }

    public Book getById(int id) {
        if (!books.containsKey(id)) {
            throw new IllegalArgumentException("No book with such id: " + id);
        }

        return books.get(id);
    }

    public List<Book> getAllWhere(Predicate<Book> predicate) {
        return books.values().stream().filter(predicate).collect(Collectors.toList());
    }

    public void updateAuthor(Book book, String newAuthor) {
        checkForBookInShelf(book);
        book.setAuthor(newAuthor);
    }

    public void updateAuthor(int id, String newAuthor) {
        checkForBookInShelf(id);
        updateAuthor(books.get(id), newAuthor);
    }

    public void updateName(Book book, String newName) {
        checkForBookInShelf(book);
        book.setName(newName);
    }

    public void updateName(int id, String newName) {
        checkForBookInShelf(id);
        updateName(books.get(id), newName);
    }

    public void updateYear(Book book, int newYear) {
        checkForBookInShelf(book);
        book.setYear(newYear);
    }

    public void updateYear(int id, int newYear) {
        checkForBookInShelf(id);
        updateYear(books.get(id), newYear);
    }

    public void deleteAllBooks() {
        books.clear();
    }

    public void deleteBook(Book book) {
        checkForBookInShelf(book);
        books.remove(book.getId());
    }

    public void deleteById(int id) {
        checkForBookInShelf(id);
        books.remove(id);
    }

    public void deleteAllWhere(Predicate<Book> predicate) {
        List<Book> booksToRemove = getAllWhere(predicate);
        booksToRemove.forEach(
            book -> books.remove(book.getId())
        );
    }

    private void checkForBookInShelf(int id) {
        if (!books.containsKey(id)) {
            throw new NoSuchElementException("No book with id: " + id);
        }
    }

    private void checkForBookInShelf(Book book) {
        if (!books.containsValue(book)) {
            throw new NoSuchElementException("No book with id: " + book);
        }
    }
}
