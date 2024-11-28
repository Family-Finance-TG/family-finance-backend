package com.fatecmogi.family_finance.domain.exception;

public class FFUnauthorizedAccessException extends RuntimeException {
    public FFUnauthorizedAccessException(String action) {
        super(String.format("You are not authorized to perform the action: %s", action));
    }
}
