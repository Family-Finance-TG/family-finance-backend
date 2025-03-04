package com.fatecmogi.family_finance.family.domain.mapper;

import com.fatecmogi.family_finance.family.application.dto.request.CreateFamilyDTO;
import com.fatecmogi.family_finance.family.application.dto.request.UpdateFamilyDTO;
import com.fatecmogi.family_finance.family.application.dto.response.FamilyDetailsResponseDTO;
import com.fatecmogi.family_finance.family.application.dto.response.FamilySummaryResponseDTO;
import com.fatecmogi.family_finance.user.domain.mapper.UserMapper;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class}
)
public interface FamilyMapper {
    FamilyDetailsResponseDTO toDetailsDTO(Family entity);
    FamilySummaryResponseDTO toSummaryDTO(Family entity);

    Family toEntity(CreateFamilyDTO dto);

    Family updateEntity(UpdateFamilyDTO dto, @MappingTarget Family entity);
}
