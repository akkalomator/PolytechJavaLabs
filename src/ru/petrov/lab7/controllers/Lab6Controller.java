package ru.petrov.lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.xml.sax.SAXException;
import ru.petrov.lab6.transactions.Account;
import ru.petrov.lab6.transactions.Transaction;
import ru.petrov.lab6.transactions.managers.AsynchronousTransactionManager;
import ru.petrov.lab6.transactions.managers.TransactionManager;
import ru.petrov.lab6.utils.XMLTransactionsLoader;
import ru.petrov.lab7.utils.DialogManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Lab6Controller implements Initializable {

    @FXML
    public TextField pathTextField;

    @FXML
    public TableView<Transaction> transactionsTable;

    @FXML
    public TableColumn<Transaction, Integer> idColumn;

    @FXML
    public TableColumn<Transaction, Integer> fromColumn;

    @FXML
    public TableColumn<Transaction, Integer> toColumn;

    @FXML
    public TableColumn<Transaction, Integer> amountColumn;

    @FXML
    public TableColumn<Transaction, Boolean> completedColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("reciever"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
    }

    public void onOpenButtonClicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(pathTextField.getScene().getWindow());
        if (file != null) {
            pathTextField.setText(file.getPath());
        }
    }

    public void onCompleteButtonClicked(ActionEvent actionEvent) {
        Map<Integer, Account> accounts = generateSampleAccounts();
        File file = new File(pathTextField.getText());
        XMLTransactionsLoader loader = new XMLTransactionsLoader();
        List<Transaction> transactions;
        try {
            transactions = loader.load(file, accounts);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            DialogManager.showDialog(
                "Error",
                "Cannot read from file",
                Alert.AlertType.ERROR
            );
            return;
        }
        TransactionManager manager = new AsynchronousTransactionManager(transactions);
        manager.completeTransactions();
        transactionsTable.getItems().setAll(transactions);
    }

    private Map<Integer, Account> generateSampleAccounts() {
        Map<Integer, Account> accounts = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            accounts.put(i, new Account(i, 100000));
        }
        return accounts;
    }
}
