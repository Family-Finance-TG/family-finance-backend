package com.fatecmogi.family_finance.family_invite.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

public record FamilyInviteStatusDTO(
        String value,
        String friendlyName
) implements IDTO {
}
