package com.fatecmogi.family_finance.user.application.dto.request;

import com.fatecmogi.family_finance.common.application.dto.IDTO;
import com.fatecmogi.family_finance.user.application.dto.response.GenderDTO;

import java.time.LocalDateTime;

public record CreateUserDTO(
        String name,
        String accessName,
        String password,
        LocalDateTime dateBirth,
        String cpf,
        GenderDTO gender
) implements IDTO {
}
