package com.fatecmogi.family_finance.application.dto.user.response;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.user.gender.GenderDTO;

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
