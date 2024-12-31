package com.fatecmogi.family_finance.domain.mapper.family.debt;

import com.fatecmogi.family_finance.application.dto.family.debt.request.CreateFamilyDebtDTO;
import com.fatecmogi.family_finance.application.dto.family.debt.response.FamilyDebtDetailsResponseDTO;
import com.fatecmogi.family_finance.application.dto.family.debt.response.FamilyDebtSummaryResponseDTO;
import com.fatecmogi.family_finance.domain.mapper.user.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.family.debt.FamilyDebt;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = GenderMapper.class
)
public interface FamilyDebtMapper{

    FamilyDebtDetailsResponseDTO toDetailsDTO(FamilyDebt entity);
    FamilyDebtSummaryResponseDTO toSummaryDTO(FamilyDebt entity);

    FamilyDebt toEntity(CreateFamilyDebtDTO dto);
}
