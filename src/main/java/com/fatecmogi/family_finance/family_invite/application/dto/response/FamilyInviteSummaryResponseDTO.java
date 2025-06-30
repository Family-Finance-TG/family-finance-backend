package com.fatecmogi.family_finance.family_invite.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;
import com.fatecmogi.family_finance.family.application.dto.response.FamilySummaryResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;

public record FamilyInviteSummaryResponseDTO(
        Long id,
        UserSummaryResponseDTO creator,
        FamilySummaryResponseDTO family
) implements IDTO {
}
