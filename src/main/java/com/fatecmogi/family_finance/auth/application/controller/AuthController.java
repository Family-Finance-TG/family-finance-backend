package com.fatecmogi.family_finance.auth.application.controller;

import com.fatecmogi.family_finance.auth.application.dto.LoginRequestDTO;
import com.fatecmogi.family_finance.user.application.dto.request.CreateUserDTO;
import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.auth.domain.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signin")
    public AppResponseData login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new AppResponseData(
                HttpStatus.OK,
                service.login(loginRequestDTO)
        );
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponseData signUp(@RequestBody CreateUserDTO userDTO) {
        service.save(userDTO);
        return new AppResponseData(HttpStatus.CREATED);
    }
}

