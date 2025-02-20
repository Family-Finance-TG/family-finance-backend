package com.fatecmogi.family_finance.application.controller.auth;

import com.fatecmogi.family_finance.application.dto.auth.LoginRequestDTO;
import com.fatecmogi.family_finance.application.dto.user.request.CreateUserDTO;
import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.service.auth.AuthService;
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

