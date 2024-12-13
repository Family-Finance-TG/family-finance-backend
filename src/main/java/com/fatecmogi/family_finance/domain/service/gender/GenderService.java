package com.fatecmogi.family_finance.domain.service.gender;

import com.fatecmogi.family_finance.application.dto.gender.GenderDTO;
import com.fatecmogi.family_finance.domain.mapper.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.gender.GenderEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderService {
    private final GenderMapper mapper;

    public GenderService(GenderMapper mapper) {
        this.mapper = mapper;
    }

    public List<GenderDTO> getAll() {
        return Arrays.stream(GenderEnum.values())
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
