package ru.croc.coder.service;

public class ProblemConstrainException extends RuntimeException {

    public ProblemConstrainException(String max_attempts_exceeded) {
        super(max_attempts_exceeded);
    }
}
