package com.emazon.transaction.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    String message;

    public Response(String message) {
        this.message = message;
    }
}