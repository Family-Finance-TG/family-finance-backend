package com.fatecmogi.family_finance.common.application.controller;


import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/hello")
public class HelloController {

    @GetMapping
    public AppResponseData hello() {
        return new AppResponseData(HttpStatus.OK, "Hello World!");
    }
}