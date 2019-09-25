package ru.petrov.lab6.utils;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import ru.petrov.lab6.transactions.Account;
import ru.petrov.lab6.transactions.Transaction;
import ru.petrov.lab6.utils.XMLTransactionsLoader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class XMLTransactionsLoaderTest {

    @Test
    public void load_WorksCorrectly() throws IOException, SAXException, ParserConfigurationException {
        XMLTransactionsLoader loader = new XMLTransactionsLoader();
        File file = new File("resources/lab6/veryLittleData.xml");
        Map<Integer, Account> accounts = Map.of(
            0, new Account(0, 200),
            1, new Account(1, 150),
            2, new Account(2, 250)
        );
        List<Transaction> transactions = loader.load(file, accounts);

        assertEquals(
            List.of(
                new Transaction(0, accounts.get(1), accounts.get(2), 158),
                new Transaction(1, accounts.get(0), accounts.get(2), 87),
                new Transaction(2, accounts.get(0), accounts.get(2), 131),
                new Transaction(3, accounts.get(2), accounts.get(0), 133)
            ),
            transactions
        );
    }
}