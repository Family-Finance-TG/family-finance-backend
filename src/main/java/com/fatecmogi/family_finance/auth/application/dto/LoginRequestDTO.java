package com.fatecmogi.family_finance.auth.application.dto;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record LoginRequestDTO(
        String accessName,
        String password
) implements IDTO {
}
