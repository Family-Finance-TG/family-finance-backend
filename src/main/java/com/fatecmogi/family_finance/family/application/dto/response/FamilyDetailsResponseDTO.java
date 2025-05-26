package com.fatecmogi.family_finance.family.application.dto.response;


import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryWithPermissionsDTO;

import java.time.LocalDate;
import java.util.Set;

public record FamilyDetailsResponseDTO(
        Long id,
        String name,
        Long creatorId,
        Set<UserSummaryWithPermissionsDTO> members
) implements FamilyBaseResponseDTO {
}
