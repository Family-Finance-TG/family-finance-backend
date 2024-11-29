package com.fatecmogi.family_finance.application.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.gender.GenderDTO;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
