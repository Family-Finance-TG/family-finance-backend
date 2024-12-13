package com.fatecmogi.family_finance.domain.mapper.family;

import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.domain.mapper.BaseMapper;
import com.fatecmogi.family_finance.domain.mapper.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.Family;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = GenderMapper.class
)
public interface FamilyMapper extends BaseMapper<Family, FamilyDTO> {

    @Override
    @Mapping(target="id", ignore=true)
    Family toEntity(FamilyDTO dto);
}
