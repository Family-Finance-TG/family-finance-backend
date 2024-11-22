package com.fatecmogi.family_finance.domain.service.gender;

import com.fatecmogi.family_finance.application.dto.gender.GenderDTO;
import com.fatecmogi.family_finance.domain.mapper.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.repository.GenderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderService {
    private final GenderRepository genderRepository;
    private final GenderMapper mapper;

    public GenderService(GenderRepository genderRepository, GenderMapper mapper) {
        this.genderRepository = genderRepository;
        this.mapper = mapper;
    }

    public List<GenderDTO> getAll() {
        return genderRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
