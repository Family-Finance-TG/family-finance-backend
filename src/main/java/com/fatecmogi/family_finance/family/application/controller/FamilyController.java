package com.fatecmogi.family_finance.family.application.controller;

import com.fatecmogi.family_finance.family.application.dto.request.CreateFamilyDTO;
import com.fatecmogi.family_finance.family.application.dto.request.UpdateFamilyDTO;
import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.family.domain.service.FamilyService;
import com.fatecmogi.family_finance.user.domain.service.UserService;
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
    public AppResponseData create(@RequestBody CreateFamilyDTO familyDTO, JwtAuthenticationToken token) {
        return new AppResponseData(
                HttpStatus.CREATED,
                familyService.save(familyDTO, token)
        );
    }

    @GetMapping("/{id}")
    public AppResponseData readById(@PathVariable("id") Long id) {
        return new AppResponseData(
                HttpStatus.OK,
                familyService.findById(id)
        );
    }

    @GetMapping("/{id}/members")
    public AppResponseData findByIdWithMembers(@PathVariable("id") Long id) {
        return new AppResponseData(
                HttpStatus.OK,
                familyService.findByIdWithMembers(id)
        );
    }

//    @PatchMapping("/{familyId}/users/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    public AppResponseData addMember(@PathVariable("familyId") long familyId, @PathVariable("userId") long userId, JwtAuthenticationToken token) {
//        return new AppResponseData(
//                HttpStatus.OK,
//                familyService.addMember(familyId, userId, token)
//        );
//    }

    @PatchMapping("/{familyId}")
    @ResponseStatus(HttpStatus.OK)
    public AppResponseData changeName(@PathVariable("familyId") long familyId, @RequestBody UpdateFamilyDTO familyDTO, JwtAuthenticationToken token) {
        return new AppResponseData(
                HttpStatus.OK,
                familyService.update(familyId, familyDTO, token)
        );
    }

    @DeleteMapping("/{familyId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AppResponseData removeMember(@PathVariable("familyId") long familyId, @PathVariable("userId") long userId, JwtAuthenticationToken token) {
        return new AppResponseData(
                HttpStatus.OK,
                familyService.removeMember(familyId, userId, token)
        );
    }
}
