package com.fatecmogi.family_finance.application.controller.family_debt;

import com.fatecmogi.family_finance.application.dto.family_debt.FamilyDebtDTO;
import com.fatecmogi.family_finance.application.util.AppResponseData;
import com.fatecmogi.family_finance.domain.service.family_debt.FamilyDebtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/families/{familyId}/family-debts")
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

}
