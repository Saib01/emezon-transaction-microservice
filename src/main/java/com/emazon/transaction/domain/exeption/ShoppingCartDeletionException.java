package com.emazon.transaction.domain.exeption;

import com.emazon.transaction.domain.utils.ExceptionResponseDomain;

public class ShoppingCartDeletionException extends RuntimeException {
    public ShoppingCartDeletionException(ExceptionResponseDomain message) {
        super(message.getMessage());
    }
}
