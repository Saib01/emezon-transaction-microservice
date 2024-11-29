package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.domain.utils.ExceptionResponseDomain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(ExceptionResponseDomain message) {
        super(message.getMessage());
    }
}
