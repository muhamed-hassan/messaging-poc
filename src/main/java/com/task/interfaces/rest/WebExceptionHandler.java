package com.task.interfaces.rest;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.task.application.EntityNotFoundException;
import com.task.application.NoResultException;

@RestControllerAdvice
public class WebExceptionHandler {

    private static final String ERROR_KEY = "error";

    @ExceptionHandler(value = { EntityNotFoundException.class, NoResultException.class })
    public ResponseEntity<Map<String, String>> handleNoResultException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Map.of(ERROR_KEY, exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException exception) {
        var message = exception.getConstraintViolations()
                                       .stream()
                                       .map(ConstraintViolation::getMessage)
                                       .collect(Collectors.joining(", "));
        return responseFrom(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var message = exception.getBindingResult()
                                       .getAllErrors()
                                       .stream()
                                       .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                       .collect(Collectors.joining(", "));
        return responseFrom(HttpStatus.BAD_REQUEST, message);
    }


    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception exception) {
        var message = exception.getMessage() == null ? "Unable to process this request." : exception.getMessage();
        return responseFrom(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private ResponseEntity<Map<String, String>> responseFrom(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                             .body(Map.of(ERROR_KEY, message));
    }

}
