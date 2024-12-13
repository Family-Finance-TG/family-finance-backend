package com.fatecmogi.family_finance.application.dto.family.debt.payment_status;

import com.fatecmogi.family_finance.application.dto.IDTO;

public record PaymentStatusDTO(
        String friendlyName,
        String value
) implements IDTO {
}
