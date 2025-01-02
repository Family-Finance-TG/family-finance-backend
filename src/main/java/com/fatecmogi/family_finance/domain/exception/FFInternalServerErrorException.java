package com.fatecmogi.family_finance.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FFInternalServerErrorException extends RuntimeException {
    private final HttpStatus status;

    public FFInternalServerErrorException() {
        this("An unexpected error occurred. Please try again later or contact support.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected FFInternalServerErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
