package ru.croc.coder.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.croc.coder.service.exceptions.ExerciseConstrainException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.croc.coder.service.exceptions.ServiceException;

import java.util.HashMap;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleExerciseConstraintException(ExerciseConstrainException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                    new ExceptionDto(ex.getClass().getName(),
                        ex.getMessage(),
                        new HashMap<String, String>(){{put("sessionId", request.getSessionId());}}),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST,
                    request);
    }
}
