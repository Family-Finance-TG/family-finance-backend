package com.fatecmogi.family_finance.application.dto.family.response;

import com.fatecmogi.family_finance.application.dto.family.debt.response.FamilyDebtSummaryResponseDTO;
import com.fatecmogi.family_finance.application.dto.user.response.UserSummaryResponseDTO;

import java.util.Set;

public record FamilyDetailsResponseDTO(
        Long id,
        String name,
        Set<UserSummaryResponseDTO> members,
        Set<FamilyDebtSummaryResponseDTO> debts
) implements FamilyBaseResponseDTO {
}
