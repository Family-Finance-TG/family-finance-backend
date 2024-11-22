package com.fatecmogi.family_finance.domain.mapper.gender;

import com.fatecmogi.family_finance.application.dto.gender.GenderDTO;
import com.fatecmogi.family_finance.domain.mapper.IMapper;
import com.fatecmogi.family_finance.infrastructure.entity.Gender;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper implements IMapper<Gender, GenderDTO> {

    @Override
    public GenderDTO toDTO(Gender entity) {
        return new GenderDTO(
                entity.getId(),
                entity.getValue(),
                entity.getFriendlyName()
        );
    }

    @Override
    public Gender toEntity(GenderDTO dto) {
        Gender entity = new Gender();
        updateEntity(dto, entity);
        return entity;
    }

    @Override
    public void updateEntity(GenderDTO dto, Gender entity) {
        entity.setValue(dto.value());
        entity.setFriendlyName(dto.friendlyName());
    }
}
