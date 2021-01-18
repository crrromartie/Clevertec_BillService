package ru.clevertec.bill.exception;

public class DaoException extends Exception {
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
