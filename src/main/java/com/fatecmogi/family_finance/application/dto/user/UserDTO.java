package com.fatecmogi.family_finance.application.dto.user;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.gender.GenderDTO;
import com.fatecmogi.family_finance.infrastructure.entity.Gender;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String name,
        String accessName,
        String password,
        LocalDateTime dateBirth,
        float salary,
        float percentageSalary,
        String cpf,
        GenderDTO gender
) implements IDTO {
}
