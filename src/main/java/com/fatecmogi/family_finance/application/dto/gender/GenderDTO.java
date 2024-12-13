package com.fatecmogi.family_finance.application.dto.gender;

import com.fatecmogi.family_finance.application.dto.IDTO;

public record GenderDTO(
        String value,
        String friendlyName
) implements IDTO {
}