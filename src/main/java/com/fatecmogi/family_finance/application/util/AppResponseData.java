package com.fatecmogi.family_finance.application.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.paginate.PageResponseDTO;
import com.fatecmogi.family_finance.domain.exception.FFInternalServerErrorException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class AppResponseData {
    private final int status;
    private final String message;
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private Object data;

    public AppResponseData(HttpStatus status) {
        this.status = status.value();
        this.message = status.name();
    }

    public AppResponseData(HttpStatus status, IDTO data) {
        this(status);
        this.data = data;
    }

    public AppResponseData(HttpStatus status, List<? extends IDTO> data) {
        this(status);
        this.data = data;
    }

    public AppResponseData(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public AppResponseData(HttpStatus status, String message, List<? extends IDTO> data) {
        this(status, message);
        this.data = data;
    }

    public AppResponseData(HttpStatus status, String message, PageResponseDTO<? extends IDTO> data){
        this(status, message);
        this.data = data;
    }

    public AppResponseData(FFInternalServerErrorException exception) {
        this(exception.getStatus(), exception.getMessage());
    }
}