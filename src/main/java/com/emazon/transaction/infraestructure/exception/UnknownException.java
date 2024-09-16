package com.emazon.transaction.infraestructure.exception;

import com.emazon.transaction.infraestructure.exceptionhandler.ExceptionResponse;

public class UnknownException extends RuntimeException {
    public UnknownException(ExceptionResponse message) {
        super(message.getMessage());
    }
}

