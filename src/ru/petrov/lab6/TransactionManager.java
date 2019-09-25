package ru.petrov.lab6;

import java.util.List;

public abstract class TransactionManager {

    protected List<Transaction> transactions;

    public TransactionManager(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public abstract void completeTransactions();
}
