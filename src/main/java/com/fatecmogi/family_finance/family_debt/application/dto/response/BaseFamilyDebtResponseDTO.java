package com.fatecmogi.family_finance.family_debt.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public interface BaseFamilyDebtResponseDTO extends IDTO {
    Long id();
    String title();
    double value();
}
