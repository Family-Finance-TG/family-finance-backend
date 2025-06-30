package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFUnauthorizedException extends FFInternalServerErrorException {
    public FFUnauthorizedException() {
        super("A autenticação falhou, verifique as suas credenciais.", HttpStatus.UNAUTHORIZED);
    }

    public FFUnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
