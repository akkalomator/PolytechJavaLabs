package ru.petrov.lab6;

import ru.petrov.lab6.exceptions.TransactionFailedException;

import java.util.Objects;

public class Transaction {

    private final int id;
    private final Account from;
    private final Account to;
    private final int amount;
    private boolean isCompleted;

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
        this.isCompleted = false;
    }

    public void completeTransaction() {
        if (isCompleted) {
            throw new TransactionFailedException(
                String.format(
                    "Transaction %d from %d to %d: Transaction has already been executed",
                    id, from.getId(), to.getId())
            );
        }
        if (!from.haveEnoughMoney(amount)) {
            throw new TransactionFailedException(
                String.format(
                    "Transaction %d from %d to %d: Not enough money",
                    id, from.getId(), to.getId())
            );
        }
        from.changeAmount(-this.amount);
        to.changeAmount(this.amount);
        isCompleted = true;
    }

    public int getId() {
        return id;
    }

    public Account getSender() {
        return from;
    }

    public Account getReciever() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
            amount == that.amount &&
            Objects.equals(from, that.from) &&
            Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, amount);
    }
}
