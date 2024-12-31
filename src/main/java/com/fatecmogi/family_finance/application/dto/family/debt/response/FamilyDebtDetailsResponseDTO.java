package com.fatecmogi.family_finance.application.dto.family.debt.response;

import com.fatecmogi.family_finance.application.dto.user.response.UserSummaryResponseDTO;

import java.time.LocalDateTime;

public record FamilyDebtDetailsResponseDTO(
        Long id,
        String title,
        String description,
        double value,
        UserSummaryResponseDTO creator,
        UserSummaryResponseDTO responsible,
        LocalDateTime dueDate,
        String paymentStatus
) implements BaseFamilyDebtResponseDTO {
}
