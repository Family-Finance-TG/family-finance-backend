package com.fatecmogi.family_finance.application.dto.family.response;

public record FamilySummaryResponseDTO(
        Long id,
        String name
) implements FamilyBaseResponseDTO {
}
