package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.domain.utils.ExceptionResponseDomain;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(ExceptionResponseDomain message) {
        super(message.getMessage());
    }
}
