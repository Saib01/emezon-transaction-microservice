package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.domain.utils.ExceptionResponseDomain;

public class InvalidSupplyException extends RuntimeException {
    public InvalidSupplyException(ExceptionResponseDomain message) {
        super(message.getMessage());
    }
}