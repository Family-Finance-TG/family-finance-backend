package com.fatecmogi.family_finance.user.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record UserSummaryResponseDTO(
        Long id,
        String name
) implements UserBaseResponseDTO, IDTO{
}
