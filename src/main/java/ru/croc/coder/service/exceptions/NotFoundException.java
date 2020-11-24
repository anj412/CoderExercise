package ru.croc.coder.service.exceptions;

public class NotFoundException extends ServiceException {
    public NotFoundException() {super();}

    public NotFoundException(String string) {
        super(string);
    }

}
