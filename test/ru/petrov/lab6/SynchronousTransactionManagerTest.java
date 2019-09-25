package ru.petrov.lab6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SynchronousTransactionManagerTest {

    private static List<Transaction> transactions;
    private static Map<Integer, Account> accounts;

    @BeforeAll
    public static void loadTransactions() throws ParserConfigurationException, IOException, SAXException {
        accounts = Map.of(
            0, new Account(0, 200),
            1, new Account(1, 150),
            2, new Account(2, 250)
        );
        XMLTransactionsLoader loader = new XMLTransactionsLoader();
        File input = new File("resources/lab6/littleData.xml");
        transactions = loader.load(input, accounts);
    }

    @Test
    public void completeTransactions_WorksCorrectly() {

        SynchronousTransactionManager stm = new SynchronousTransactionManager(transactions);
        stm.completeTransactions();

        assertEquals(130, accounts.get(0).getAmount());
        assertEquals(222, accounts.get(1).getAmount());
        assertEquals(248, accounts.get(2).getAmount());
    }

}