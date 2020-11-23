package ru.croc.coder.service.exceptions;

public class PermissionException extends ServiceException {
    public PermissionException(String string) {
        super(string);
    }

    public PermissionException() {
        super();
    }
}
