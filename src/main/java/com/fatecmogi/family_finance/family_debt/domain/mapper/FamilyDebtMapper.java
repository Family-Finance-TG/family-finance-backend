package com.fatecmogi.family_finance.family_debt.domain.mapper;

import com.fatecmogi.family_finance.family_debt.application.dto.request.CreateFamilyDebtDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.response.FamilyDebtDetailsResponseDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.response.FamilyDebtSummaryResponseDTO;
import com.fatecmogi.family_finance.user.domain.mapper.GenderMapper;
import com.fatecmogi.family_finance.family_debt.infrastructure.entity.FamilyDebt;
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
