package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFResourceNotFoundException extends FFInternalServerErrorException {
    public FFResourceNotFoundException() {
        super("Resource not found.", HttpStatus.NOT_FOUND);
    }

    public FFResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
