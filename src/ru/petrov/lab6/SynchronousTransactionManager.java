package ru.petrov.lab6;

import ru.petrov.lab6.exceptions.TransactionFailedException;

import java.util.List;

public class SynchronousTransactionManager extends TransactionManager{

    public SynchronousTransactionManager(List<Transaction> transactions) {
        super(transactions);
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
