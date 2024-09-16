package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.domain.utils.ExceptionResponseDomain;

public class InvalidIdProductException extends RuntimeException {
    public InvalidIdProductException(ExceptionResponseDomain message) {
        super(message.getMessage());
    }
}