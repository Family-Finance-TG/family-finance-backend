package com.fatecmogi.family_finance.application.controller.family;

import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.service.family.user.FamilyService;
import com.fatecmogi.family_finance.domain.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/families")
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService, UserService userService) {
        this.familyService = familyService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponseData create(@RequestBody FamilyDTO familyDTO, JwtAuthenticationToken token) {
        return new AppResponseData(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.name(),
                familyService.save(familyDTO, token)
        );
    }

    @GetMapping("/{id}")
    public AppResponseData readById(@PathVariable("id") Long id) {
        return new AppResponseData(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                familyService.findById(id)
        );
    }
}
