package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.domain.utils.ExceptionResponseDomain;

public class ProductNotFoundInCart extends RuntimeException {
    public ProductNotFoundInCart(ExceptionResponseDomain message) {
        super(message.getMessage());
    }
}