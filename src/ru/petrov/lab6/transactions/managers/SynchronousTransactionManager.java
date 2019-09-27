package ru.petrov.lab6.transactions.managers;

import ru.petrov.lab6.exceptions.TransactionFailedException;
import ru.petrov.lab6.transactions.Transaction;

import java.util.List;

public class SynchronousTransactionManager implements TransactionManager {

    protected List<Transaction> transactions;

    public SynchronousTransactionManager(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void completeTransactions() {
        System.Logger logger = System.getLogger("transactions");
        transactions.forEach(transaction -> {
            try {
                transaction.completeTransaction();
                logger.log(System.Logger.Level.INFO, transaction.getId() + " executed");
            } catch (TransactionFailedException e) {
                logger.log(System.Logger.Level.INFO, transaction.getId() + " not executed");
            }
        });
    }
}