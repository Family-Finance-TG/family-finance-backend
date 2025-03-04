package com.fatecmogi.family_finance.family_invite.application.controller;

import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.family_invite.application.dto.request.CreateFamilyInviteRequestDTO;
import com.fatecmogi.family_finance.family_invite.domain.service.FamilyInviteService;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class FamilyInviteController {

    private final FamilyInviteService familyInviteService;

    public FamilyInviteController(FamilyInviteService familyInviteService) {
        this.familyInviteService = familyInviteService;
    }

    @PostMapping("/families/{familyId}/invites")
    public AppResponseData createInvite(
            @PathVariable("familyId") Long familyId,
            @RequestBody CreateFamilyInviteRequestDTO createFamilyInviteDTO,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.CREATED,
                familyInviteService.save(
                        familyId,
                        createFamilyInviteDTO,
                        token
                )
        );
    }

    @GetMapping("/families/{familyId}/invites")
    public AppResponseData findAll(
            @PathVariable("familyId") Long familyId,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                familyInviteService.findAll(familyId, token)
        );
    }

    @GetMapping("/families/{familyId}/invites/{inviteId}")
    public AppResponseData findById(
            @PathVariable("familyId") Long familyId,
            @PathVariable("inviteId") Long inviteId,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                familyInviteService.findById(familyId, inviteId, token)
        );
    }

    @PatchMapping("/families/{familyId}/invites/{inviteId}/cancel")
    public AppResponseData cancelInvite(
            @PathVariable("familyId") Long familyId,
            @PathVariable("inviteId") Long inviteId,
            JwtAuthenticationToken token
    ) {
        familyInviteService.cancelInvite(familyId, inviteId, token);
        return new AppResponseData(
                HttpStatus.OK,
                "Invite canceled"
        );
    }

    @GetMapping("/users/{receiverId}/invites")
    public AppResponseData findAllByUserId(
            @PathVariable("receiverId") Long receiverId,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                familyInviteService.findAllByUserId(receiverId, token)
        );
    }

    @GetMapping("users/{receiverId}/invites/{inviteId}")
    public AppResponseData findByIdByUserId(
            @PathVariable("receiverId") Long receiverId,
            @PathVariable("inviteId") Long inviteId,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                familyInviteService.findByIdByUserId(receiverId, inviteId, token)
        );
    }

    @PatchMapping("/users/{receiverId}/invites/{inviteId}/accept")
    public AppResponseData acceptInvite(
            @PathVariable("receiverId") Long receiverId,
            @PathVariable("inviteId") Long inviteId,
            JwtAuthenticationToken token
    ) {
        familyInviteService.acceptInvite(receiverId, inviteId, token);
        return new AppResponseData(
                HttpStatus.OK,
                "Invite accepted"
        );
    }

    @PatchMapping("/users/{receiverId}/invites/{inviteId}/reject")
    public AppResponseData rejectInvite(
            @PathVariable("receiverId") Long receiverId,
            @PathVariable("inviteId") Long inviteId,
            JwtAuthenticationToken token
    ) {
        familyInviteService.rejectInvite(receiverId, inviteId, token);
        return new AppResponseData(
                HttpStatus.OK,
                "Invite rejected"
        );
    }

}
