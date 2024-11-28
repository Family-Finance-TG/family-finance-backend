package com.fatecmogi.family_finance.application.controller.exception;

import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(FFAuthenticationException.class)
    AppResponseData handleAuthenticationException(FFAuthenticationException e){
        return new AppResponseData(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(FFUnauthorizedAccessException.class)
    AppResponseData handleUnauthorizedAccessException(FFUnauthorizedAccessException e){
        return new AppResponseData(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FFResourceNotFoundException.class)
    AppResponseData handleResourceNotFoundException(FFResourceNotFoundException e){
        return new AppResponseData(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(FFResourceAlreadyExistsException.class)
    AppResponseData handleResourceAlreadyExistsException(FFResourceAlreadyExistsException e){
        return new AppResponseData(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FFInvalidDataException.class)
    AppResponseData handleInvalidDataException(FFInvalidDataException e){
        return new AppResponseData(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FFMandatoryFieldException.class)
    AppResponseData handleMandatoryFieldException(FFMandatoryFieldException e){
        return new AppResponseData(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FFInternalServerErrorException.class)
    AppResponseData handleInternalServerErrorException(FFInternalServerErrorException e){
        return new AppResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    AppResponseData handleException(Exception e){
        return new AppResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
