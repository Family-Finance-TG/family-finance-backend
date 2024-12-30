package com.fatecmogi.family_finance.application.dto.user.request;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.user.gender.GenderDTO;

import java.time.LocalDateTime;

public record UpdateUserDTO(
        String name,
        LocalDateTime dateBirth,
        float salary,
        float percentageSalary,
        String cpf,
        GenderDTO gender
) implements IDTO {
}
