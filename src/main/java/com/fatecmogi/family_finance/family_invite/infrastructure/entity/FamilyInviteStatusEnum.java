package com.fatecmogi.family_finance.family_invite.infrastructure.entity;

import com.fatecmogi.family_finance.family_invite.application.dto.response.FamilyInviteStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FamilyInviteStatusEnum {
    PENDING("PENDING", "Pending"),
    ACCEPTED("ACCEPTED", "Accepted"),
    REJECTED("REJECTED", "Rejected"),
    CANCELED("CANCELED", "Canceled");

    private final String value;
    private final String friendlyName;

    public static FamilyInviteStatusEnum fromValue(String value) {
        for (FamilyInviteStatusEnum status : FamilyInviteStatusEnum.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown family invite status value: " + value);
    }

    public static FamilyInviteStatusEnum fromDtoValue(FamilyInviteStatusDTO dto) {
        for (FamilyInviteStatusEnum status : FamilyInviteStatusEnum.values()) {
            if (status.value.equals(dto.value())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown family invite status value: " + dto.value());
    }

}
