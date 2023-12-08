package com.cameleoon.quotes.exceptions;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(String message) {
        super(message);
    }
}
