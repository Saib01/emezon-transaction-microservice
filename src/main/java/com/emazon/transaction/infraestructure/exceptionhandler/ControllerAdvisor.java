package com.emazon.transaction.infraestructure.exceptionhandler;

import com.emazon.transaction.domain.exeption.InvalidSupplyException;
import com.emazon.transaction.infraestructure.exception.FeignProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;


@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "Message";

    private ResponseEntity<Map<String, String>> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(Collections.singletonMap(MESSAGE, message));
    }

    @ExceptionHandler(FeignProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidSupplyException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
