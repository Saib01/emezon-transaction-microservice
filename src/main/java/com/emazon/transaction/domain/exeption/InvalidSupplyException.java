package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.infraestructure.exceptionhandler.ExceptionResponse;

public class InvalidSupplyException extends RuntimeException {
    public InvalidSupplyException(ExceptionResponse message) {
        super(message.getMessage());
    }
}