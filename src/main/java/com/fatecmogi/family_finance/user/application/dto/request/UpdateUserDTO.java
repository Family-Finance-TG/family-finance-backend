package com.fatecmogi.family_finance.user.application.dto.request;

import com.fatecmogi.family_finance.common.application.dto.IDTO;
import com.fatecmogi.family_finance.user.application.dto.response.GenderDTO;

import java.time.LocalDateTime;

public record UpdateUserDTO(
        String name,
        LocalDateTime dateBirth,
        String cpf,
        GenderDTO gender
) implements IDTO {
}
