package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFMandatoryFieldException extends FFInternalServerErrorException {
    public FFMandatoryFieldException() {
        super("A mandatory field is missing or empty.", HttpStatus.BAD_REQUEST);
    }

    public FFMandatoryFieldException(String fieldName) {
        super(String.format("Field '%s' is mandatory and cannot be null or empty.", fieldName), HttpStatus.BAD_REQUEST);
    }
}
