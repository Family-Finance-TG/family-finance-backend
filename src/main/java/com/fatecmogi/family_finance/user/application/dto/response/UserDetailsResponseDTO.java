package com.fatecmogi.family_finance.user.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

import java.time.LocalDateTime;

public record UserDetailsResponseDTO(
        Long id,
        String name,
        LocalDateTime dateBirth,
        float salary,
        float percentageSalary,
        String cpf,
        GenderDTO gender
) implements UserBaseResponseDTO, IDTO {
}
