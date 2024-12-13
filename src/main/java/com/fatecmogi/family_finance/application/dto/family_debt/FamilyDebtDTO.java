package com.fatecmogi.family_finance.application.dto.family_debt;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;

import java.time.LocalDateTime;

public record FamilyDebtDTO(
        long id,
        String title,
        String description,
        float value,
        FamilyDTO family,
        UserDTO creator,
        UserDTO responsible,
        LocalDateTime dueDate,
        String paymentStatus
) implements IDTO {
}
