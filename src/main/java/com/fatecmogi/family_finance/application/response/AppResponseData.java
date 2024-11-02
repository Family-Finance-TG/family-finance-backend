package com.fatecmogi.family_finance.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.PageResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class AppResponseData {

    private final int code;
    private final String message;
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private Object data;

    public AppResponseData(int code, String message, IDTO data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AppResponseData(int code, String message, List<? extends IDTO> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AppResponseData(int code, String message, PageResponseDTO<? extends IDTO> data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AppResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }
}