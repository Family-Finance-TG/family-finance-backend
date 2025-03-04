package com.fatecmogi.family_finance.auth.application.dto;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record LoginResponseDTO(
        String accessToken,
        Long expiresIn
) implements IDTO {
}
