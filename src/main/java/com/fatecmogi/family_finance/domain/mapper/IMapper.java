package com.fatecmogi.family_finance.domain.mapper;

import com.fatecmogi.family_finance.application.dto.IDTO;

public interface IMapper<E, D extends IDTO> {
    D toDTO(E entity);
    E toEntity(D dto);
    void updateEntity(D dto, E entity);
}
