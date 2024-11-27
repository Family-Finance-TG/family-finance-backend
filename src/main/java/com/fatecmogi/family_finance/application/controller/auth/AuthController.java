package com.fatecmogi.family_finance.application.controller.auth;

import com.fatecmogi.family_finance.application.dto.auth.LoginRequestDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public AppResponseData login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new AppResponseData(
                200,
                "OK",
                service.login(loginRequestDTO)
        );
    }

    @PostMapping("/signup")
    public AppResponseData signUp(@RequestBody UserDTO userDTO) {
        service.save(userDTO);
        return new AppResponseData(HttpStatus.CREATED.value(), "Usuário criado com sucesso");
    }
}

