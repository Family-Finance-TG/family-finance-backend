package com.fatecmogi.family_finance.application.dto.auth;

import com.fatecmogi.family_finance.application.dto.IDTO;

public record LoginResponseDTO(
        String accessToken,
        Long expiresIn
) implements IDTO {
}
