package com.paintblend.domain.exceptions;

public class ColorNotFoundException extends RuntimeException {
    public ColorNotFoundException(String message) {
        super(message);
    }
}
