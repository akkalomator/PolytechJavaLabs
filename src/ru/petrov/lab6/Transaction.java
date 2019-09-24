package ru.petrov.lab6;

import ru.petrov.lab6.exceptions.TransactionFailedException;

public class Transaction {

    private final int id;
    private final Account from;
    private final Account to;
    private final int amount;

    public Transaction(int id, Account from, Account to, int amount) {

        if (from == null || to == null) {
            throw new IllegalArgumentException("Accounts cannot be be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative, try to swap sender and recipient of transaction");
        }

        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public void completeTransaction() {
        if (!from.haveEnoughMoney(amount)) {
            throw new TransactionFailedException("Not enough money");
        }
        from.changeAmount(-this.amount);
        to.changeAmount(this.amount);
    }

    public int getId() {
        return id;
    }
}
