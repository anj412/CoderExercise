package ru.croc.coder.service.exceptions;

public class NotPassedRestrictionsException extends ServiceException {
    public NotPassedRestrictionsException(String string) {
        super(string);
    }

    public NotPassedRestrictionsException () {
        super();
    }
}
