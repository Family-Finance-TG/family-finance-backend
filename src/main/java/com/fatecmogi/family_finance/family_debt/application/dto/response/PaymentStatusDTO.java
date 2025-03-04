package com.fatecmogi.family_finance.family_debt.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record PaymentStatusResponseDTO(
        String value,
        String friendlyName
) implements IDTO {
}
