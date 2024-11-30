package com.example.crud_app.exceptions;

public class BadPersonRequestException extends RuntimeException{
    public BadPersonRequestException(String message) {
        super(message);
    }
}
