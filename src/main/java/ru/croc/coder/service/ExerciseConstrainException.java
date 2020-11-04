package ru.croc.coder.service;

public class ExerciseConstrainException extends RuntimeException {

    public ExerciseConstrainException(String max_attempts_exceeded) {
        super(max_attempts_exceeded);
    }

    public ExerciseConstrainException() {
    }
}
