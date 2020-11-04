package ru.croc.coder.service;

public class NotPassedRestrictionsException extends RuntimeException {
    public NotPassedRestrictionsException(String string) {
        super(string);
    }

    public NotPassedRestrictionsException () {
        super();
    }
}
