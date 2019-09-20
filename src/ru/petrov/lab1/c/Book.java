package ru.petrov.lab1.c;

import java.util.Objects;

public class Book {

    private int id;
    private String author;
    private String name;
    private int year;

    public Book(String author, String name, int year) {

        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        id = -1;
        this.author = author;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year &&
            author.equals(book.author) &&
            name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name, year);
    }

    @Override
    public String toString() {
        return "Book:" +
            " author='" + author + '\'' +
            ", name='" + name + '\'' +
            ", year=" + year;
    }
}
