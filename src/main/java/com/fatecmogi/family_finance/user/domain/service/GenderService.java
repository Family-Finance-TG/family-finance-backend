package com.fatecmogi.family_finance.user.domain.service;

import com.fatecmogi.family_finance.user.application.dto.response.GenderDTO;
import com.fatecmogi.family_finance.user.domain.mapper.GenderMapper;
import com.fatecmogi.family_finance.user.infrastructure.entity.GenderEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenderService {
    private final GenderMapper mapper;

    public List<GenderDTO> getAll() {
        return Arrays.stream(GenderEnum.values())
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
