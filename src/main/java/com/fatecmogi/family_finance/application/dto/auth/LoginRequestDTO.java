package com.fatecmogi.family_finance.application.dto.auth;

import com.fatecmogi.family_finance.application.dto.IDTO;

public record LoginRequestDTO(
        String accessName,
        String password
) implements IDTO {
}
