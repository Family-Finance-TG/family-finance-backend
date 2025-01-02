package com.fatecmogi.family_finance.application.controller.exception;

import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FFInternalServerErrorException.class)
    public ResponseEntity<AppResponseData> handleFFBaseApiException(FFInternalServerErrorException exception) {
        AppResponseData responseData = new AppResponseData(exception);

        // Aqui estamos criando uma ResponseEntity, o que permitirá que o código de status seja ajustado no cabeçalho
        return new ResponseEntity<>(responseData, HttpStatus.valueOf(exception.getStatus().value()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppResponseData> handleException(Exception e){
        AppResponseData responseData = new AppResponseData(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        // Aqui estamos criando uma ResponseEntity, o que permitirá que o código de status seja ajustado no cabeçalho
        return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}