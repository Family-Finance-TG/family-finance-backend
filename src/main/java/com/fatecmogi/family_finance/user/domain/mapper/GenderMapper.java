package com.fatecmogi.family_finance.user.domain.mapper;


import com.fatecmogi.family_finance.user.application.dto.response.GenderDTO;
import com.fatecmogi.family_finance.user.infrastructure.entity.GenderEnum;
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

