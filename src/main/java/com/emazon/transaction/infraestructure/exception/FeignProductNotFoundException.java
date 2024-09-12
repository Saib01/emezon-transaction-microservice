package com.emazon.transaction.infraestructure.exception;

import com.emazon.transaction.infraestructure.exceptionhandler.ExceptionResponse;

public class FeignProductNotFoundException extends RuntimeException {
    public FeignProductNotFoundException(ExceptionResponse message) {
        super(message.getMessage());
    }
}
