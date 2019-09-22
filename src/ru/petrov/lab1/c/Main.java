package ru.petrov.lab1.c;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main implements AutoCloseable {

    private static Scanner sc;
    private static BookShelf shelf;

    @Override
    public void close() {
        sc.close();
    }

    public static void main(String[] args) {
        shelf = new BookShelf();
        sc = new Scanner(System.in);
        System.out.println("Welcome to Bookshelf v0.0.1\n");
        toMainMenu();

    }

    private static void toMainMenu() {
        while (true) {
            System.out.println(
                "Please, type a number of option and press Enter\n" +
                    "1 - Add book to shelf\n" +
                    "2 - List of all books\n" +
                    "3 - Get book from shelf\n" +
                    "4 - Edit book in shelf\n" +
                    "5 - Remove book from shelf\n" +
                    "\n" +
                    "0 - Exit"
            );

            int option = -1;
            do {
                try {
                    String s = sc.nextLine();
                    option = Integer.valueOf(s);
                }
                catch (NumberFormatException e) {
                    System.out.println("Type option correctly");
                }
            } while (option < 0 || option > 5);
            switch (option) {
                case 0: {
                    return;
                }
                case 1: {
                    toAddMenu();
                    break;
                }
                case 2: {
                    listAllBooks();
                    break;
                }
                case 3: {
                    getBookFromShelf();
                    break;
                }
                case 4: {
                    toBookEditorMenu();
                    break;
                }
                case 5: {
                    toDeleteBookMenu();
                    break;
                }
            }
        }

    }

    private static void toAddMenu() {
        String[] tokens = getBookTokens();

        if (tokens == null) {
            return;
        }

        try {
            shelf.addBook(
                new Book(
                    tokens[0],
                    tokens[1],
                    Integer.valueOf(tokens[2]
                    )
                )
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            toAddMenu();
        }

    }

    private static void listAllBooks() {
        List<Book> books = shelf.getAllBooks();
        books.forEach(
            System.out::println
        );
    }

    private static void getBookFromShelf() {
        System.out.println("Type the author of the book (type '-' if you don't know)");
        String author = sc.nextLine();
        System.out.println("Type the name of the book (type '-' if you don't know)");
        String name = sc.nextLine();
        System.out.println("Type the name of the book (type '-' if you don't know)");

        int year = -1;
        boolean isCorrect = false;
        boolean isYearRequired = false;
        while (!isCorrect) {
            try {
                var str = sc.nextLine();
                if (str.equalsIgnoreCase("-")) {
                    break;
                }
                year = Integer.valueOf(str);
                isCorrect = true;
                isYearRequired = true;
            } catch (NumberFormatException e) {
                System.out.println("Please, enter year in the correct format");
            }
        }

        List<Book> booksByAuthor = null;
        if (!author.equalsIgnoreCase("-")) {
            booksByAuthor = shelf.getAllWhere(book -> book.getAuthor().equalsIgnoreCase(author));
        }

        List<Book> booksByName = null;
        if (!name.equalsIgnoreCase("-")) {
            booksByName = shelf.getAllWhere(book -> book.getName().equalsIgnoreCase(name));
        }

        List<Book> booksByYear = null;
        int year2 = year;                                   // In order to make variable 'year' effectively final in order to lambda expr to compile
        if (isYearRequired) {
            booksByYear = shelf.getAllWhere(book -> book.getYear() == year2);
        }

        List<Book> result = booksByAuthor;

        if (booksByName != null) {
            if (result == null) {
                result = booksByName;
            } else {
                result = result
                    .stream()
                    .filter(booksByName::contains)
                    .collect(Collectors.toList());
            }
        }

        if (booksByYear != null) {
            if (result == null) {
                result = booksByYear;
            } else {
                result = result
                    .stream()
                    .filter(booksByYear::contains)
                    .collect(Collectors.toList());
            }
        }

        if (result == null) {
            listAllBooks();
            return;
        }

        if (result.isEmpty()) {
            System.out.println("No such book found");
        }

        result.forEach(System.out::println);

    }

    private static void toBookEditorMenu() {
        String[] tokensOfBookToChange = getBookTokens();
        if (tokensOfBookToChange == null) {
            return;
        }

        Book bookToChange;
        try {
            bookToChange = new Book(
                tokensOfBookToChange[0],
                tokensOfBookToChange[1],
                Integer.valueOf(tokensOfBookToChange[2])
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            toAddMenu();
            return;
        }


        String option;
        do {
            System.out.println("Type what you want to change (author | name | year):");
            option = sc.nextLine();
        } while (!(option.equalsIgnoreCase("author")
            || option.equalsIgnoreCase("name")
            || option.equalsIgnoreCase("year"))
        );

        System.out.println("Input new " + option);

        String replacement;
        do {
            try {
                replacement = sc.nextLine();
                if (option.equalsIgnoreCase("year")) {
                    Integer.valueOf(replacement);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, enter " + option + " in the correct format");
                replacement = null;
            }
        } while (replacement == null);

        switch (option) {
            case "author": {
                shelf.updateAuthor(bookToChange, replacement);
                break;
            }
            case "name": {
                shelf.updateName(bookToChange, replacement);
                break;
            }
            case "year": {
                shelf.updateYear(bookToChange, Integer.valueOf(replacement));
                break;
            }
        }


    }

    private static void toDeleteBookMenu() {
        String[] tokens = getBookTokens();
        if (tokens == null) {
            return;
        }
        shelf.deleteBook(new Book(tokens[0], tokens[1], Integer.valueOf(tokens[2])));
    }

    private static String[] getBookTokens() {
        String[] tokens;
        do {
            System.out.println("Please, enter book in the following form: \n" +
                "<author>:<name>:<year>\n" +
                "In order to exit, type '0' and press Enter"
            );
            String line = sc.nextLine();
            tokens = line.split(":");
            if (tokens.length == 1 && tokens[0].equals("0")) {
                return null;
            }
        } while (tokens.length != 3);
        return tokens;
    }
}
