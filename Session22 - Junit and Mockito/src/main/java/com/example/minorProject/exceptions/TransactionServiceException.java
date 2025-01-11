package com.example.minorProject.exceptions;


public class TransactionServiceException extends RuntimeException{
    public TransactionServiceException(String message) {
        super(message);
    }
}
