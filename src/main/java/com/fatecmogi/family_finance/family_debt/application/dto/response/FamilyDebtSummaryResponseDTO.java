package com.fatecmogi.family_finance.family_debt.application.dto.response;

public record FamilyDebtSummaryResponseDTO(
        Long id,
        String title,
        double value
) implements BaseFamilyDebtResponseDTO {
}
