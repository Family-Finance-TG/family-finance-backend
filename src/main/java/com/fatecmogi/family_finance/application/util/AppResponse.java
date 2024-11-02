package com.fatecmogi.family_finance.application.util;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.PageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AppResponse<T extends AppResponseData> extends ResponseEntity<T> {


    public AppResponse(HttpStatus status) {
        super(status);
    }

    public AppResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public static AppResponse<AppResponseData> success(IDTO body, String message, HttpStatus status) {
        return new AppResponse<>(new AppResponseData(HttpStatus.OK.value(), message, body), status);
    }

    public static AppResponse<AppResponseData> success(HttpStatus status) {
        return new AppResponse<>(status);
    }

    public static AppResponse<AppResponseData> success(List<? extends IDTO> body, String message, HttpStatus status) {
        return new AppResponse<>(new AppResponseData(HttpStatus.OK.value(), message, body), status);
    }

    public static AppResponse<AppResponseData> success(PageResponseDTO<? extends IDTO> body, String message, HttpStatus status){
        return new AppResponse<>(new AppResponseData(HttpStatus.OK.value(), message, body), status);
    }


    public static AppResponse<AppResponseData> success(IDTO body, String message) {
        return AppResponse.success(body, message, HttpStatus.OK);
    }

    public static AppResponse<AppResponseData> success(IDTO body) {
        return AppResponse.success(body, "", HttpStatus.OK);
    }

    public static AppResponse<AppResponseData> success(List<? extends IDTO> body) {
        return AppResponse.success(body, "", HttpStatus.OK);
    }

    public static AppResponse<AppResponseData> success(PageResponseDTO<? extends IDTO> body){
        return AppResponse.success(body, "", HttpStatus.OK);
    }

    public static AppResponse<AppResponseData> error(String message, HttpStatus status) {
        return new AppResponse<>(new AppResponseData(status.value(), message), status);
    }
}