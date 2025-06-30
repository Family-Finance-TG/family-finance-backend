package com.fatecmogi.family_finance.common.domain.exception;

import org.springframework.http.HttpStatus;

public class FFConflictException extends FFInternalServerErrorException {

  public FFConflictException()
  {
    this("Não foi possível completar a operação.");
  }

  public FFConflictException(String message)
  {
        super(message, HttpStatus.CONFLICT);
  }
}