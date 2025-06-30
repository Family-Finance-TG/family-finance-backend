package com.fatecmogi.family_finance.family_invite.domain.mapper;

import com.fatecmogi.family_finance.family_invite.application.dto.response.FamilyInviteDetailsResponseDTO;
import com.fatecmogi.family_finance.family_invite.application.dto.response.FamilyInviteSummaryResponseDTO;
import com.fatecmogi.family_finance.family_invite.infrastructure.entity.FamilyInvite;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = FamilyInviteStatusMapper.class
)
public interface FamilyInviteMapper {
    FamilyInviteDetailsResponseDTO toDetailsDTO(FamilyInvite entity);
    FamilyInviteSummaryResponseDTO toSummaryDTO(FamilyInvite entity);

    FamilyInvite toEntity(FamilyInviteDetailsResponseDTO dto);
}
