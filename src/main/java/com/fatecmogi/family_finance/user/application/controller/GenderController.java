package com.fatecmogi.family_finance.user.application.controller;

import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.user.domain.service.GenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/genders")
@AllArgsConstructor
public class GenderController {
    private final GenderService service;

    @GetMapping
    public AppResponseData getAll(){
        return new AppResponseData(
                HttpStatus.OK,
                service.getAll()
        );
    }
}
