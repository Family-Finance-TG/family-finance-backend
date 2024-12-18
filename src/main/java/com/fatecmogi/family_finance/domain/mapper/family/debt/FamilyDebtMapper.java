package com.fatecmogi.family_finance.domain.mapper.family.debt;

import com.fatecmogi.family_finance.application.dto.family.debt.FamilyDebtDTO;
import com.fatecmogi.family_finance.domain.mapper.BaseMapper;
import com.fatecmogi.family_finance.domain.mapper.user.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.family.debt.FamilyDebt;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = GenderMapper.class
)
public interface FamilyDebtMapper extends BaseMapper<FamilyDebt, FamilyDebtDTO> {
    @Override
    @Mapping(target="id", ignore=true)
    FamilyDebt toEntity(FamilyDebtDTO dto);
}
