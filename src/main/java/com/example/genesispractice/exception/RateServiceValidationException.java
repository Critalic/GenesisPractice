package com.example.genesispractice.exception;

public class RateServiceValidationException extends RuntimeException {

    public RateServiceValidationException(String errorMessage) {
        super(errorMessage);
    }

    public RateServiceValidationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
