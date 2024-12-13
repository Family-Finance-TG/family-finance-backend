package com.fatecmogi.family_finance.application.controller.family.debt;

import com.fatecmogi.family_finance.application.dto.family.debt.FamilyDebtDTO;
import com.fatecmogi.family_finance.application.dto.family.debt.payment_status.PaymentStatusDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.service.family.debt.FamilyDebtService;
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
            @RequestBody FamilyDebtDTO familyDebtDTO) {
        return new AppResponseData(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.name(),
                service.save(familyId, familyDebtDTO)
        );
    }

    @PatchMapping("{familyDebtId}/payment-status")
    public AppResponseData updatePaymentStatus(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            @RequestBody PaymentStatusDTO paymentStatusDTO) {
        return new AppResponseData(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                service.updatePaymentStatus(familyId, familyDebtId, paymentStatusDTO)
        );
    }

    @PatchMapping("{familyDebtId}/responsible")
    public AppResponseData updateResponsible(
            @PathVariable("familyId") long familyId,
            @PathVariable("familyDebtId") long familyDebtId,
            @RequestBody UserDTO responsibleId) {
        return new AppResponseData(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                service.updateResponsible(familyId, familyDebtId, responsibleId)
        );
    }
}
