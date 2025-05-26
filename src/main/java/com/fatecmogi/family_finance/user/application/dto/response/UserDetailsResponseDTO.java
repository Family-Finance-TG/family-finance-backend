package com.fatecmogi.family_finance.user.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserDetailsResponseDTO(
        Long id,
        String name,
        LocalDateTime dateBirth,
        UUID inviteCode,
        float salary,
        float percentageSalary,
        String cpf,
        GenderDTO gender,
        Long familyId,
        List<String> permissions
) implements UserBaseResponseDTO, IDTO {
}
