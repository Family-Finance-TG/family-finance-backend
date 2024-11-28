package com.fatecmogi.family_finance.domain.exception;

public class FFAuthenticationException extends RuntimeException {
    public FFAuthenticationException(String message) {
        super(message);
    }

    public FFAuthenticationException() {
        super("Authentication failed, verify your credentials.");
    }
}
