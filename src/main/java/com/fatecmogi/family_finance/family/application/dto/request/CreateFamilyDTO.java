package com.fatecmogi.family_finance.family.application.dto.request;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record CreateFamilyDTO(
        String name
) implements IDTO {
}
