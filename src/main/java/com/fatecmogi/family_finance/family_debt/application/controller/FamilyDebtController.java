package com.fatecmogi.family_finance.family_debt.application.controller;

import com.fatecmogi.family_finance.family_debt.application.dto.request.CreateFamilyDebtDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.request.UpdatePaymentStatusDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.request.UpdateResponsibleDTO;
import com.fatecmogi.family_finance.common.application.util.AppResponseData;
import com.fatecmogi.family_finance.family_debt.domain.service.FamilyDebtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/families/{familyId}/debts")
public class FamilyDebtController {
    private final FamilyDebtService service;

    public FamilyDebtController(FamilyDebtService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponseData create(
            @PathVariable("familyId") long familyId,
            @RequestBody CreateFamilyDebtDTO familyDebtDTO) {
        return new AppResponseData(
                HttpStatus.CREATED,
                service.save(familyId, familyDebtDTO)
        );
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
            @PathVariable("familyDebtId") long familyDebtId) {
        return new AppResponseData(
                HttpStatus.OK,
                service.findById(familyId, familyDebtId)
        );
    }

    @PatchMapping("{familyDebtId}/payment-status")
    public AppResponseData updatePaymentStatus(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            @RequestBody UpdatePaymentStatusDTO paymentStatusDTO) {
        return new AppResponseData(
                HttpStatus.OK,
                service.updatePaymentStatus(familyId, familyDebtId, paymentStatusDTO)
        );
    }

    @PatchMapping("{familyDebtId}/responsible")
    public AppResponseData updateResponsible(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            @RequestBody UpdateResponsibleDTO responsibleId) {
        return new AppResponseData(
                HttpStatus.OK,
                service.updateResponsible(familyId, familyDebtId, responsibleId)
        );
    }
}
