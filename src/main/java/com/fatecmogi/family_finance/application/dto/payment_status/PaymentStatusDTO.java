package com.fatecmogi.family_finance.application.dto.payment_status;

import com.fatecmogi.family_finance.application.dto.IDTO;

public record PaymentStatusDTO(
        String friendlyName,
        String value
) implements IDTO {
}
