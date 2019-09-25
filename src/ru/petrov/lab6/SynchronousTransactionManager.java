package ru.petrov.lab6;

import ru.petrov.lab6.exceptions.TransactionFailedException;

import java.util.List;

public class SynchronousTransactionManager implements TransactionManager{

    protected List<Transaction> transactions;

    public SynchronousTransactionManager(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void completeTransactions() {
        transactions.forEach(transaction -> {
            try {
                transaction.completeTransaction();
            } catch (TransactionFailedException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
