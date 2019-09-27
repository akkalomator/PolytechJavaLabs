package ru.petrov.lab6;

import org.xml.sax.SAXException;
import ru.petrov.lab6.transactions.Account;
import ru.petrov.lab6.transactions.Transaction;
import ru.petrov.lab6.transactions.managers.AsynchronousTransactionManager;
import ru.petrov.lab6.utils.XMLTransactionsLoader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionsDemo {

    public static void main(String[] args) {

        Map<Integer, Account> accounts = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            accounts.put(i, new Account(i, 500000));
        }

        XMLTransactionsLoader loader = new XMLTransactionsLoader();
        File input = new File("resources/lab6/data.xml");
        try {
            List<Transaction> transactions = loader.load(input, accounts);
            AsynchronousTransactionManager stm = new AsynchronousTransactionManager(transactions);
            stm.completeTransactions();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
