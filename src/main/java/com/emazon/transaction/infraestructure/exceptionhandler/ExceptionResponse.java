package com.emazon.transaction.infraestructure.exceptionhandler;


public enum ExceptionResponse {
    STOCK_FEIGN_PRODUCT_NOT_FOUND("The product to add supply was not found"),
    UNKNOWN_ERROR("An unknown error has occurred. Please try again later or contact support if the issue persists."),
    JWT_INVALID("Token Invalid, not Authorized");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}