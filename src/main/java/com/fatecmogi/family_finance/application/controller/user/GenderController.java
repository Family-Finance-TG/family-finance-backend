package com.fatecmogi.family_finance.application.controller.user;

import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.service.user.gender.GenderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/genders")
public class GenderController {
    private final GenderService service;

    public GenderController(GenderService service) {
        this.service = service;
    }

    @GetMapping
    public AppResponseData getAll(){
        return new AppResponseData(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                service.getAll()
        );
    }
}
