package com.fatecmogi.family_finance.family_debt.application.dto.request;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record UpdatePaymentStatusDTO(
    String value
) implements IDTO {
}
