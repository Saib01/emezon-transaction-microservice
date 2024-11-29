package com.emazon.transaction.infraestructure.exception;


public class ConnectionRefusedException extends RuntimeException {
    public ConnectionRefusedException(String message) {
        super(message
        );
    }
}
