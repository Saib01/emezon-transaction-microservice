package com.emazon.transaction.infraestructure.exceptionhandler;

import com.emazon.transaction.domain.exeption.*;
import com.emazon.transaction.infraestructure.exception.ConnectionRefusedException;
import com.emazon.transaction.domain.exeption.ProductNotFoundException;
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

    @ExceptionHandler({
            ProductNotFoundException.class,
            ProductNotFoundInCart.class,
            ShoppingCartDeletionException.class
    })
    public ResponseEntity<Map<String, String>> handleNotFoundExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({InvalidSupplyException.class,
            InvalidIdProductException.class})
    public ResponseEntity<Map<String, String>> handleBadRequestExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ExceptionHandler({
            ConnectionRefusedException.class
    })
    public ResponseEntity<Map<String, String>> handleInternalServerExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler({
            InsufficientStockException.class,
            ReportRegistrationException.class
    })
    public ResponseEntity<Map<String, String>> handleConflictExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }
}
