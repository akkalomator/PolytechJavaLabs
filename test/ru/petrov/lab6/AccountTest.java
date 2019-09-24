package ru.petrov.lab6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void constructor_ThrowsOnInitialMoneyLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Account(0, -1));
    }

    @Test
    public void changeAmount_ThrowsOnResultingAmountLessThanZero() {
        Account account = new Account(0, 50);
        assertThrows(NotEnoughMoneyExeption.class, () -> account.changeAmount(-100));
    }

    @Test
    public void changeAmount_WorksCorrectly() {
        Account account = new Account(0, 100);
        account.changeAmount(50);
        assertEquals(150, account.getAmount());

        account.changeAmount(-100);
        assertEquals(50, account.getAmount());
    }
}