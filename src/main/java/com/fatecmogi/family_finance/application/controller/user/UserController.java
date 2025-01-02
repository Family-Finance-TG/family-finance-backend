package com.fatecmogi.family_finance.application.controller.user;

import com.fatecmogi.family_finance.application.dto.user.request.UpdateUserDTO;
import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

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
        return new AppResponseData(HttpStatus.OK, "Usuário excluido com sucesso");    }

}
