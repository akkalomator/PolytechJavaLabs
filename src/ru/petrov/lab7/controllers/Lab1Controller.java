package ru.petrov.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import ru.petrov.lab1.c.Book;
import ru.petrov.lab1.c.BookShelf;
import ru.petrov.lab7.utils.DialogManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Lab1Controller implements Initializable {

    @FXML
    public TextField bookAuthorField;

    @FXML
    public TextField bookNameField;

    @FXML
    public TextField bookYearField;

    @FXML
    public TableView<Book> booksTable;

    @FXML
    public TableColumn<Book, Integer> bookIdColumn;

    @FXML
    public TableColumn<Book, Integer> bookYearColumn;

    @FXML
    public TableColumn<Book, String> bookAuthorColumn;

    @FXML
    public TableColumn<Book, String> bookNameColumn;

    private final BookShelf shelf;

    private Predicate<Book> bookPredicate = (book -> {
        boolean authorMatches = true;
        String author = bookAuthorField.getText();
        if (!author.isBlank()) {
            authorMatches = book.getAuthor().equals(author);
        }
        boolean nameMatches = true;
        String name = bookNameField.getText();
        if (!name.isBlank()) {
            nameMatches = book.getName().equals(name);
        }
        boolean yearMatches = true;
        String yearStr = bookYearField.getText();
        if (!yearStr.isBlank()) {
            int year = Integer.parseInt(yearStr);
            yearMatches = book.getYear() == year;
        }

        return authorMatches && nameMatches && yearMatches;
    });

    public Lab1Controller() {
        shelf = new BookShelf();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        booksTable.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Book book = row.getItem();
                    bookAuthorField.setText(book.getAuthor());
                    bookNameField.setText(book.getName());
                    bookYearField.setText(String.valueOf(book.getYear()));
                }
            });
            return row;
        });

        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }


    public void onAddButtonClicked(ActionEvent actionEvent) {
        String author = bookAuthorField.getText();
        if (author.isBlank()) {
            DialogManager.showDialog(
                "Author name is not valid",
                "Author name can not be \"\"",
                Alert.AlertType.ERROR
            );
            return;
        }
        String name = bookNameField.getText();
        if (name.isBlank()) {
            DialogManager.showDialog(
                "Book name is not valid",
                "Book name can not be \"\"",
                Alert.AlertType.ERROR
            );
            return;
        }
        int year;
        try {
            year = Integer.parseInt(bookYearField.getText());
        } catch (NumberFormatException e) {
            DialogManager.showDialog(
                "Book year is not valid",
                "Book year can not be \"" + bookYearField.getText() + "\"",
                Alert.AlertType.ERROR
            );
            return;
        }
        Book book = new Book(author, name, year);
        shelf.addBook(book);
        booksTable.getItems().add(book);
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
        if (!bookYearField.getText().isBlank()) {
            try {
                Integer.parseInt(bookYearField.getText());
            } catch (NumberFormatException e) {
                DialogManager.showDialog(
                    "Book year is not valid",
                    "Book year can not be \"" + bookYearField.getText() + "\n",
                    Alert.AlertType.ERROR
                );
                return;
            }
        }
        shelf.deleteAllWhere(bookPredicate);
        booksTable.getItems().setAll(shelf.getAllBooks());
    }

    public void onAllButtonClicked(ActionEvent actionEvent) {
        booksTable.getItems().setAll(shelf.getAllBooks());
    }

    public void onFilterButtonClicked(ActionEvent actionEvent) {
        if (!bookYearField.getText().isBlank()) {
            try {
                Integer.parseInt(bookYearField.getText());
            } catch (NumberFormatException e) {
                DialogManager.showDialog(
                    "Book year is not valid",
                    "Book year can not be \"" + bookYearField.getText() + "\n",
                    Alert.AlertType.ERROR
                );
                return;
            }
        }
        List<Book> books = shelf.getAllWhere(bookPredicate);
        booksTable.getItems().setAll(books);
    }
}
