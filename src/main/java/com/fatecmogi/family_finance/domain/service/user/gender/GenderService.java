package com.fatecmogi.family_finance.domain.service.user.gender;

import com.fatecmogi.family_finance.application.dto.user.gender.GenderDTO;
import com.fatecmogi.family_finance.domain.mapper.user.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.user.gender.GenderEnum;
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
