package com.emazon.transaction.domain.utils;


public enum ExceptionResponseDomain {
    SUPPLY_IS_INVALID("The supply increment must be greater than zero."),
    ID_PRODUCT_IS_INVALID("The id product must be greater than zero.");

    private final String message;

    ExceptionResponseDomain(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}