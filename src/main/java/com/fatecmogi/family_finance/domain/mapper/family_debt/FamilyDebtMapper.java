package com.fatecmogi.family_finance.domain.mapper.family_debt;

import com.fatecmogi.family_finance.application.dto.family_debt.FamilyDebtDTO;
import com.fatecmogi.family_finance.domain.mapper.BaseMapper;
import com.fatecmogi.family_finance.infrastructure.entity.family_debt.FamilyDebt;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FamilyDebtMapper extends BaseMapper<FamilyDebt, FamilyDebtDTO> {
}
