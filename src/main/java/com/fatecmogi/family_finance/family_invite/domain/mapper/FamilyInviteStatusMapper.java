package com.fatecmogi.family_finance.family_invite.domain.mapper;


import com.fatecmogi.family_finance.family_invite.application.dto.response.FamilyInviteStatusDTO;
import com.fatecmogi.family_finance.family_invite.infrastructure.entity.FamilyInviteStatusEnum;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FamilyInviteStatusMapper {

    String toString(FamilyInviteStatusEnum familyInviteStatusEnum);

    default FamilyInviteStatusDTO toDto(FamilyInviteStatusEnum familyInviteStatusEnum) {
        if (familyInviteStatusEnum == null) {
            return null;
        }
        return new FamilyInviteStatusDTO(familyInviteStatusEnum.getValue(), familyInviteStatusEnum.getFriendlyName());
    }

    default FamilyInviteStatusEnum toEnum(FamilyInviteStatusDTO familyInviteStatusDTO) {
        if (familyInviteStatusDTO == null) {
            return null;
        }
        return FamilyInviteStatusEnum.fromDtoValue(familyInviteStatusDTO);
    }

    default FamilyInviteStatusEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return FamilyInviteStatusEnum.fromValue(value);
    }

}
