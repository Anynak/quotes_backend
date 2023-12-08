package com.cameleoon.quotes.exceptions;

public class MultipleVotingException extends RuntimeException {
    public MultipleVotingException(String message) {
        super(message);
    }
}
