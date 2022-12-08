package com.task.interfaces.rest;

import java.util.Collections;
import java.util.Map;
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

@RestControllerAdvice
public class RestErrorHandler {
	
	private static final String ERROR = "error";
	
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException exception) {
        var message = exception.getConstraintViolations()
                                       .stream()
                                       .map(ConstraintViolation::getMessage)
                                       .collect(Collectors.joining(", "));
        return responseFrom(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleWebExchangeBindException(WebExchangeBindException exception) {
        var message = exception.getBindingResult()
                               .getAllErrors()
                               .stream()
                               .map(DefaultMessageSourceResolvable::getDefaultMessage)
                               .collect(Collectors.joining(", "));
        return responseFrom(exception.getStatus(), message);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleServerWebInputException(ServerWebInputException exception) {
        return responseFrom(exception.getStatus(), exception.getReason());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception exception) {
        var message = exception.getMessage() == null ? "Unable to process this request." : exception.getMessage();
        return responseFrom(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private ResponseEntity<Map<String, String>> responseFrom(HttpStatus httpStatus, String message) {
    	var error = Collections.singletonMap(ERROR, message);
        return ResponseEntity.status(httpStatus)
                             .body(error);
    }

}
