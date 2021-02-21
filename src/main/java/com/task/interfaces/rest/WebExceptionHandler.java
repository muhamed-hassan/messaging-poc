package com.task.interfaces.rest;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import com.task.application.EntityNotFoundException;
import com.task.application.NoResultException;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class, NoResultException.class })
    public ResponseEntity<Error> handleDataNotFoundRelatedExceptions(RuntimeException exception) {
        return responseFrom(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException exception) {
        var message = exception.getConstraintViolations()
                                       .stream()
                                       .map(ConstraintViolation::getMessage)
                                       .collect(Collectors.joining(", "));
        return responseFrom(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleWebExchangeBindException(WebExchangeBindException exception) {
        var message = exception.getBindingResult()
                               .getAllErrors()
                               .stream()
                               .map(DefaultMessageSourceResolvable::getDefaultMessage)
                               .collect(Collectors.joining(", "));
        return responseFrom(exception.getStatus(), message);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleServerWebInputException(ServerWebInputException exception) {
        return responseFrom(exception.getStatus(), exception.getReason());
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleGeneralException(Exception exception) {
        var message = exception.getMessage() == null ? "Unable to process this request." : exception.getMessage();
        return responseFrom(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private ResponseEntity<Error> responseFrom(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                             .body(new Error(message));
    }
    
    private static class Error {
        
        private final String error;

        public Error(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
        
    }

}
