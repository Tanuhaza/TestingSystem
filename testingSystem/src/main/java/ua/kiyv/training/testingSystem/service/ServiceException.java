package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.exception.ApplicationException;

public class ServiceException extends ApplicationException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super( cause, message);
    }

}
