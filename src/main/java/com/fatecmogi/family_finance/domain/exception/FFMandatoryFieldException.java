package com.fatecmogi.family_finance.domain.exception;

public class FFMandatoryFieldException extends RuntimeException {
    public FFMandatoryFieldException(String fieldName) {
        super(String.format("Field '%s' is mandatory and cannot be null or empty.", fieldName));
    }
}
