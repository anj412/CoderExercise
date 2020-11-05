package ru.croc.coder.service.exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException(String string) {
        super(string);
    }

    public ServiceException() {
        super();
    }
}
