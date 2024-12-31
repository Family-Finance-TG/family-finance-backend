package com.fatecmogi.family_finance.application.dto.family.debt.response;

import com.fatecmogi.family_finance.application.dto.IDTO;

public interface BaseFamilyDebtResponseDTO extends IDTO {
    Long id();
    String title();
    double value();
}
