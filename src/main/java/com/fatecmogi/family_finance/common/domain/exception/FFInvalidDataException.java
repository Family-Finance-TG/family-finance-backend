package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFInvalidDataException extends FFInternalServerErrorException {
    public FFInvalidDataException() {
        super("Invalid data provided. Please check your input and try again.", HttpStatus.BAD_REQUEST);
    }

    public FFInvalidDataException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
