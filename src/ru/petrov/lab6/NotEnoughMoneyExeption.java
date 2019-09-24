package ru.petrov.lab6;

public class NotEnoughMoneyExeption extends RuntimeException {
    public NotEnoughMoneyExeption() {
    }

    public NotEnoughMoneyExeption(String message) {
        super(message);
    }

    public NotEnoughMoneyExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyExeption(Throwable cause) {
        super(cause);
    }

    public NotEnoughMoneyExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
