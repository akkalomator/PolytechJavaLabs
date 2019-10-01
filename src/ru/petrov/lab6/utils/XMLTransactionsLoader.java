package ru.petrov.lab6.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.petrov.lab6.transactions.Account;
import ru.petrov.lab6.transactions.Transaction;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XMLTransactionsLoader {

    public List<Transaction> load(File file, Map<Integer, Account> accounts) throws ParserConfigurationException, IOException, SAXException {
        List<Transaction> transactions = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(file);
        int count = Integer.parseInt(((Element) doc.getFirstChild()).getAttribute("count"));
        NodeList nodes = doc.getElementsByTagName("transaction");
        for (int i = 0; i < count; i++) {
            Element el = (Element) nodes.item(i);
            int id = Integer.parseInt(el.getAttribute("id"));
            int from = Integer.parseInt(el.getAttribute("from"));
            int to = Integer.parseInt(el.getAttribute("to"));
            int amount = Integer.parseInt(el.getAttribute("amount"));
            transactions.add(
                new Transaction(
                    id,
                    accounts.get(from),
                    accounts.get(to),
                    amount
                )
            );
        }
        return transactions;
    }
}
