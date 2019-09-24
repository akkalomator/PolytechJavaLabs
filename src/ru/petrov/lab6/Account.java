package ru.petrov.lab6;

import ru.petrov.lab6.exceptions.NotEnoughMoneyException;

public class Account {

    private final int id;
    private int amount;

    public Account(int id, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Initial money cannot be a negative number");
        }
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    void changeAmount(int amount) {
        if (this.amount + amount < 0) {
            throw new NotEnoughMoneyException("Not enough money to complete transaction");
        }
        this.amount += amount;
    }

    boolean haveEnoughMoney(int amount) {
        return this.amount >= amount;
    }
}
