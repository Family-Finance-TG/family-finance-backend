package com.fatecmogi.family_finance.domain.mapper.family;

import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.application.dto.family.request.CreateFamilyDTO;
import com.fatecmogi.family_finance.application.dto.family.request.UpdateFamilyDTO;
import com.fatecmogi.family_finance.application.dto.family.response.FamilyDetailsResponseDTO;
import com.fatecmogi.family_finance.application.dto.family.response.FamilySummaryResponseDTO;
import com.fatecmogi.family_finance.domain.mapper.user.UserMapper;
import com.fatecmogi.family_finance.infrastructure.entity.family.Family;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class}
)
public interface FamilyMapper {
    FamilyDTO toDTO(Family entity);
    FamilyDetailsResponseDTO toDetailsDTO(Family entity);
    FamilySummaryResponseDTO toSummaryDTO(Family entity);

    Family toEntity(CreateFamilyDTO dto);

    Family updateEntity(UpdateFamilyDTO dto, @MappingTarget Family entity);
}
