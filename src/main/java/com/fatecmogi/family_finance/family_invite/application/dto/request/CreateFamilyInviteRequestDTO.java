package com.fatecmogi.family_finance.family_invite.application.dto.request;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

import java.util.UUID;

public record CreateFamilyInviteRequestDTO(
        UUID receiverInviteCode
) implements IDTO {
}
