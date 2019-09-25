package ru.petrov.lab6;

import ru.petrov.lab6.exceptions.NotEnoughMoneyException;

import java.util.Objects;

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

    synchronized void changeAmount(int amount) {
        if (this.amount + amount < 0) {
            throw new NotEnoughMoneyException("Not enough money to complete transaction");
        }
        this.amount += amount;
    }

    synchronized boolean haveEnoughMoney(int amount) {
        return this.amount >= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
