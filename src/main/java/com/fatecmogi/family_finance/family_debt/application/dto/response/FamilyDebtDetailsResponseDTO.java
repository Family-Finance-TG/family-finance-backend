package com.fatecmogi.family_finance.family_debt.application.dto.response;

import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;

import java.time.LocalDateTime;

public record FamilyDebtDetailsResponseDTO(
        Long id,
        String title,
        String description,
        double value,
        UserSummaryResponseDTO creator,
        UserSummaryResponseDTO responsible,
        LocalDateTime dueDate,
        PaymentStatusDTO paymentStatus
) implements BaseFamilyDebtResponseDTO {
}
