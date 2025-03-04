package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFAuthenticationException extends FFInternalServerErrorException {
    public FFAuthenticationException() {
        super("Authentication failed, verify your credentials.", HttpStatus.UNAUTHORIZED);
    }

    public FFAuthenticationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
