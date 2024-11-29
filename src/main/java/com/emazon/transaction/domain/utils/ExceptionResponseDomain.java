package com.emazon.transaction.domain.utils;


public enum ExceptionResponseDomain {
    SUPPLY_IS_INVALID("The supply increment must be greater than zero."),
    ID_PRODUCT_IS_INVALID("The id product must be greater than zero."),
    INSUFFICIENT_STOCK("The product stock quantity is insufficient for the purchase you want to make."),
    PRODUCT_NOT_FOUND("No product with that number was found in the cart"),
    SHOPPING_CART_DELETION("Failed to delete from shopping cart"),
    REPORT_REGISTRATION("Failed to register the report"),
    STOCK_FEIGN_PRODUCT_NOT_FOUND("The product to add supply was not found");

    private final String message;

    ExceptionResponseDomain(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}