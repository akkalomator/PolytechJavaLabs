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

        transactions = new ArrayList<>();
        accounts = Map.of(
            0, new Account(0, 200),
            1, new Account(1, 150),
            2, new Account(2, 250)
        );

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File("resources/lab6/littleData.xml");
        Document doc = builder.parse(file);
        NodeList nodes = doc.getElementsByTagName("transaction");
        for (int i = 0; i < 10; i++) {
            Element el = (Element) nodes.item(i);
            int id = Integer.valueOf(el.getAttribute("id"));
            int from = Integer.valueOf(el.getAttribute("from"));
            int to = Integer.valueOf(el.getAttribute("to"));
            int amount = Integer.valueOf(el.getAttribute("amount"));
            transactions.add(
                new Transaction(
                    id,
                    accounts.get(from),
                    accounts.get(to),
                    amount
                )
            );
        }
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