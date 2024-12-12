package com.fatecmogi.family_finance.domain.mapper;

import com.fatecmogi.family_finance.application.dto.IDTO;
import org.mapstruct.MappingTarget;

public interface BaseMapper <E, D extends IDTO> {
    D toDTO(E entity);
    E toEntity(D dto);
    void updateEntity(D dto, @MappingTarget E entity);
}
