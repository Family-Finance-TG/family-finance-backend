package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFResourceAlreadyExistsException extends FFInternalServerErrorException {
    public FFResourceAlreadyExistsException() {
        super("Resource already exists.", HttpStatus.BAD_REQUEST);
    }

    public FFResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s '%s' already exists", resourceName, fieldName, fieldValue), HttpStatus.BAD_REQUEST);
    }
}
