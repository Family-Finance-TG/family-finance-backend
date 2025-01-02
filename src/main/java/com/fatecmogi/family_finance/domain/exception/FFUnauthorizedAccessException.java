package com.fatecmogi.family_finance.domain.exception;

import org.springframework.http.HttpStatus;

public class FFUnauthorizedAccessException extends FFInternalServerErrorException {
    public FFUnauthorizedAccessException() {
        super("You are not authorized to perform this action.", HttpStatus.FORBIDDEN);
    }

    public FFUnauthorizedAccessException(String action) {
        super(String.format("You are not authorized to perform the action: %s", action), HttpStatus.FORBIDDEN);
    }
}
