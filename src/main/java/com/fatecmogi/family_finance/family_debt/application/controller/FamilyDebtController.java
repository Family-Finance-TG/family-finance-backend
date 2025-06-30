package com.fatecmogi.family_finance.family_debt.application.controller;

import com.fatecmogi.family_finance.family_debt.application.dto.request.*;
import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.family_debt.domain.service.FamilyDebtService;
import com.fatecmogi.family_finance.auth.domain.util.PermissionValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/families/{familyId}/debts")
@AllArgsConstructor
public class FamilyDebtController {
    private final FamilyDebtService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponseData create(
            @PathVariable("familyId") long familyId,
            @RequestBody CreateFamilyDebtDTO familyDebtDTO,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.CREATED,
                service.save(familyId, familyDebtDTO)
        );
    }

    @PostMapping("/recurring")
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponseData createRecurring(
            @PathVariable("familyId") long familyId,
            @RequestBody CreateRecurringDebtsDTO recurringDTO,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.CREATED,
                service.saveRecurringDebts(familyId, recurringDTO)
        );
    }

    @PatchMapping("{familyDebtId}")
    public AppResponseData updateDebt(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            @RequestBody UpdateFamilyDebtDTO dto,
            JwtAuthenticationToken token
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                service.updateDebt(token, familyId, familyDebtId, dto)
        );
    }

    @DeleteMapping("{familyDebtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            JwtAuthenticationToken token
    ) {
        service.delete(token, familyId, familyDebtId);
    }

    @GetMapping
    public AppResponseData findAll(@PathVariable("familyId") long familyId) {
        return new AppResponseData(
                HttpStatus.OK,
                service.findAll(familyId)
        );
    }

    @GetMapping("{familyDebtId}")
    public AppResponseData findById(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                service.findById(familyId, familyDebtId)
        );
    }

    @PatchMapping("{familyDebtId}/payment-status")
    public AppResponseData updatePaymentStatus(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            @RequestBody UpdatePaymentStatusDTO paymentStatusDTO
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                service.updatePaymentStatus(familyId, familyDebtId, paymentStatusDTO)
        );
    }

    @PatchMapping("{familyDebtId}/responsible")
    public AppResponseData updateResponsible(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            @RequestBody UpdateResponsibleDTO responsibleId
    ) {
        return new AppResponseData(
                HttpStatus.OK,
                service.updateResponsible(familyId, familyDebtId, responsibleId)
        );
    }
}
