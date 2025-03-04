package com.fatecmogi.family_finance.family.application.dto.response;

public record FamilySummaryResponseDTO(
        Long id,
        String name
) implements FamilyBaseResponseDTO {
}
