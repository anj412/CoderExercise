package ru.croc.coder.service.exceptions;

public class ExerciseConstrainException extends ServiceException {

    public ExerciseConstrainException(String max_attempts_exceeded) {
        super(max_attempts_exceeded);
    }

    public ExerciseConstrainException() {
    }
}
