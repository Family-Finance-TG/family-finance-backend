package com.fatecmogi.family_finance.user.application.controller;

import com.fatecmogi.family_finance.user.application.dto.request.UpdateUserDTO;
import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.user.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public AppResponseData findAll() {
        return new AppResponseData(
                HttpStatus.OK,
                service.findAll()
        );
    }

    @GetMapping("/{id}")
    public AppResponseData findById(@PathVariable("id") Long id) {
        return new AppResponseData(
                HttpStatus.OK,
                service.findById(id)
        );
    }

    @PutMapping("/{id}")
    public AppResponseData update(@PathVariable("id") Long id, @RequestBody UpdateUserDTO dto) {
        return new AppResponseData(
                HttpStatus.OK,
                service.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public AppResponseData delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new AppResponseData(HttpStatus.OK, "Conta inativada com sucesso");
    }


    @PatchMapping("/{id}/leave-family")
    public AppResponseData leaveFamily(@PathVariable Long id) {
        return new AppResponseData(
                HttpStatus.OK,
                service.leaveFamily(id)
        );
    }
}
