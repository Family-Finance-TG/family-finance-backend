package com.fatecmogi.family_finance.application.dto.family.debt.request;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;

import java.time.LocalDateTime;

public record CreateFamilyDebtDTO(
        String title,
        String description,
        float value,
        Long creatorId,
        Long responsibleId,
        LocalDateTime dueDate,
        String paymentStatus
) implements IDTO {
}
