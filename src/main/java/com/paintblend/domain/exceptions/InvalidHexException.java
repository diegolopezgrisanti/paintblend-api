package com.paintblend.domain.exceptions;

public class InvalidHexException extends RuntimeException {
    public InvalidHexException(String message) {
        super(message);
    }
}
