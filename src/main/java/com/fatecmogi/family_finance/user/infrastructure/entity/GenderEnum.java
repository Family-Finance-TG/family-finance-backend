package com.fatecmogi.family_finance.user.infrastructure.entity;

import com.fatecmogi.family_finance.user.application.dto.response.GenderDTO;
import com.fatecmogi.family_finance.common.domain.exception.FFInvalidDataException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {
    NOT_DEFINED("NOT_DEFINED", "Não Definido"),
    FEMALE("FEMALE", "Feminino"),
    MALE("MALE", "Masculino");

    private final String value;
    private final String friendlyName;

    public static GenderEnum fromValue(String value) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.value.equals(value)) {
                return gender;
            }
        }
        throw new FFInvalidDataException("Unknown gender value: " + value);
    }

    public static GenderEnum fromDtoValue(GenderDTO dto) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.value.equals(dto.value())) {
                return gender;
            }
        }
        throw new FFInvalidDataException("Unknown gender value: " + dto.value());
    }
}
