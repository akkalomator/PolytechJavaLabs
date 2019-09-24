package ru.petrov.lab6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.petrov.lab6.exceptions.TransactionFailedException;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Account account1;
    private Account account2;

    @BeforeEach
    public void createAccounts() {
        account1 = new Account(0, 50);
        account2 = new Account(1, 1000);
    }

    @Test
    public void constructor_ThrowsIfAccountsEitherOfAccountsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(0, account1, null, 50));
        assertThrows(IllegalArgumentException.class, () -> new Transaction(0, null, account2, 50));
        assertThrows(IllegalArgumentException.class, () -> new Transaction(0, null, null, 50));
    }

    @Test
    public void constructor_ThrowsOnAmountLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(0, account1, account2, -50));
    }

    @Test
    public void completeTransaction_ThrowsOnUnableToMakeTransaction() {
        Transaction transaction = new Transaction(0, account1, account2, 500);
        assertThrows(TransactionFailedException.class, transaction::completeTransaction);
    }

    @Test
    public void completeTransaction_WorksCorrectly() {
        Transaction transaction1 = new Transaction(0, account2, account1, 500);
        transaction1.completeTransaction();
        assertEquals(550, account1.getAmount());
        assertEquals(500, account2.getAmount());
    }

}