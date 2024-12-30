package com.fatecmogi.family_finance.application.dto.family.response;

import com.fatecmogi.family_finance.application.dto.family.debt.FamilyDebtDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;

import java.util.Set;

public record FamilyDetailsResponseDTO(
        Long id,
        String name,
        Set<UserDTO> members,
        Set<FamilyDebtDTO> debts
) implements FamilyBaseResponseDTO {
}
