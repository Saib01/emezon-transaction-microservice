package com.emazon.transaction.infraestructure.exceptionhandler;


public enum ExceptionResponse {
    CONNECTION_REFUSED("Service unavailable. Please try again later.");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}