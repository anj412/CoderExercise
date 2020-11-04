package ru.croc.coder.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.croc.coder.service.ExerciseConstrainException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

   /* @ExceptionHandler(ExerciseConstrainException.class)
    public ResponseEntity<Object> handleExerciseConstraintException(ExerciseConstrainException ex, WebRequest request) {
        return handleExeptionInternal(ex,
                    new ExceptionDto(ex.getClass().getName(),
                        ex.getMessage(),
                        new HashMap<String, String>(){{put("sessionId", request.getSessionId());}}),
                    new HttpHeaders(),
                    new HttpStatus.BAD_REQUEST,
                    request);


    }*/
}
