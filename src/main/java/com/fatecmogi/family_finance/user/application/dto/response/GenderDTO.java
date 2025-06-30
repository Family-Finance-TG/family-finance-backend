package com.fatecmogi.family_finance.user.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record GenderDTO(
        String value,
        String friendlyName
) implements IDTO {
}