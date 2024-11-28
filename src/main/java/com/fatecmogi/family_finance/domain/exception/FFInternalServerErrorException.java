package com.fatecmogi.family_finance.domain.exception;

public class FFInternalServerErrorException extends RuntimeException {
    public FFInternalServerErrorException(String message) {
        super(message);
    }
}
