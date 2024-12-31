package com.fatecmogi.family_finance.application.dto.family.debt.response;

public record FamilyDebtSummaryResponseDTO(
        Long id,
        String title,
        double value
) implements BaseFamilyDebtResponseDTO {
}
