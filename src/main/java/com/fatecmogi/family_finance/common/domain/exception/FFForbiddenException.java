package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFForbiddenException extends FFInternalServerErrorException {
    public FFForbiddenException() {
        super("Você não está autorizado a realizar essa operação. Entre em contato com o criador da família.", HttpStatus.FORBIDDEN);
    }

    public FFForbiddenException(String action) {
        super(String.format("Não está autorizado a executar a ação: %s", action), HttpStatus.FORBIDDEN);
    }
}
