package com.fatecmogi.family_finance.application.dto.user.response;

import com.fatecmogi.family_finance.application.dto.IDTO;

public record UserSummaryResponseDTO(
        Long id,
        String name
) implements UserBaseResponseDTO, IDTO{
}
