package ru.petrov.lab6.transactions;

import org.junit.jupiter.api.Test;
import ru.petrov.lab6.exceptions.NotEnoughMoneyException;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void constructor_ThrowsOnInitialMoneyLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Account(0, -1));
    }

    @Test
    void changeAmount_ThrowsOnResultingAmountLessThanZero() {
        Account account = new Account(0, 50);
        assertThrows(NotEnoughMoneyException.class, () -> account.changeAmount(-100));
    }

    @Test
    void changeAmount_WorksCorrectly() {
        Account account = new Account(0, 100);
        account.changeAmount(50);
        assertEquals(150, account.getAmount());

        account.changeAmount(-100);
        assertEquals(50, account.getAmount());
    }

    @Test
    void haveEnoughMoney_WorksCorrectly() {
        Account account = new Account(0, 50);
        assertTrue(account.haveEnoughMoney(0));
        assertTrue(account.haveEnoughMoney(49));
        assertTrue(account.haveEnoughMoney(50));
        assertFalse(account.haveEnoughMoney(51));
        assertFalse(account.haveEnoughMoney(100));
    }
}