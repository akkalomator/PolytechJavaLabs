package ru.petrov.lab6;

import java.util.List;

public class SynchronousTransactionManager extends TransactionManager{

    public SynchronousTransactionManager(List<Transaction> transactions) {
        super(transactions);
    }

    @Override
    public void completeTransactions() {

    }
}
