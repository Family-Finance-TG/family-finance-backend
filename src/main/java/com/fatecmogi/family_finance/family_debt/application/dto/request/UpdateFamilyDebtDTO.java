package com.fatecmogi.family_finance.family_debt.application.dto.request;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

import java.time.LocalDateTime;

public record UpdateFamilyDebtDTO(
        String title,
        String description,
        float value,
        LocalDateTime dueDate,
        Long responsibleId
) implements IDTO {}