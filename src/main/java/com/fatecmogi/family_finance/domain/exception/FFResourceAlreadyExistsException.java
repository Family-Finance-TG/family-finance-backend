package com.fatecmogi.family_finance.domain.exception;

public class FFResourceAlreadyExistsException extends RuntimeException {
    public FFResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
      super(String.format("%s with %s '%s' already exists", resourceName, fieldName, fieldValue));
    }
}
