package com.fatecmogi.family_finance.family.application.dto.response;

import com.fatecmogi.family_finance.family_debt.application.dto.response.FamilyDebtSummaryResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;

import java.util.Set;

public record FamilyDetailsResponseDTO(
        Long id,
        String name,
        Set<UserSummaryResponseDTO> members,
        Set<FamilyDebtSummaryResponseDTO> debts
) implements FamilyBaseResponseDTO {
}
