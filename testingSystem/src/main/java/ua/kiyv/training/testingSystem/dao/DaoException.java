package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.exception.ApplicationException;

public class DaoException extends ApplicationException {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, String messageKey) {
        super(message, messageKey);
    }

    public DaoException(Throwable cause, String message ) {
        super(cause, message);
    }
}
