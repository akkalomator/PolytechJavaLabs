package ru.petrov.lab1.c;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BookShelf {

    public BookShelf() {
    }

    public void addBook(Book book) {
    }

    public void addBook(String author, String name, int year) {
    }

    public Collection<Book> getAllBooks() {
        return null;
    }

    public Book getById(int id) {
        return null;
    }

    public Collection<Book> getAllWhere(Predicate<Book> predicate) {
        return null;
    }

    public void updateAuthor(Book book, String newAuthor) {

    }

    public void updateAuthor(int id, String newAuthor) {
    }

    public void updateName(Book book, String newName) {

    }

    public void updateName(int id, String newName) {
    }

    public void updateYear(Book book, int newYear) {

    }

    public void updateYear(int id, int newYear) {
    }

    public void deleteAllBooks() {
    }

    public void deleteBook(Book book) {

    }

    public void deleteById(int id) {

    }

    public void deleteAllWhere(Predicate<Book> predicate) {

    }
}
