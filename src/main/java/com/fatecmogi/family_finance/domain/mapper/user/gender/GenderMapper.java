package com.fatecmogi.family_finance.domain.mapper.user.gender;


import com.fatecmogi.family_finance.application.dto.user.gender.GenderDTO;
import com.fatecmogi.family_finance.infrastructure.entity.user.gender.GenderEnum;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GenderMapper {

    String toString(GenderEnum genderEnum);

    default GenderDTO toDto(GenderEnum genderEnum) {
        if (genderEnum == null) {
            return null;
        }
        return new GenderDTO(genderEnum.getValue(), genderEnum.getFriendlyName());
    }

    default GenderEnum toEnum(GenderDTO genderDTO) {
        if (genderDTO == null) {
            return null;
        }
        return GenderEnum.fromDtoValue(genderDTO);

    }

    default GenderEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return GenderEnum.fromValue(value);
    }

}

