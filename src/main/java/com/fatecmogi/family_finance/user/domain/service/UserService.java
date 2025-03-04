package com.fatecmogi.family_finance.user.domain.service;

import com.fatecmogi.family_finance.common.application.dto.IDTO;
import com.fatecmogi.family_finance.user.application.dto.request.UpdateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;
import com.fatecmogi.family_finance.user.domain.mapper.UserMapper;
import com.fatecmogi.family_finance.user.domain.mapper.GenderMapper;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final GenderMapper genderMapper;

    public UserService(UserRepository userRepository, UserMapper mapper, GenderMapper genderMapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.genderMapper = genderMapper;
    }

    public List<UserSummaryResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(mapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public UserDetailsResponseDTO findById(Long id) {
        return mapper.toDetailsDTO(userRepository.findById(id).orElseThrow());
    }

    private void updatePre(UpdateUserDTO dto, User entity) {
        entity.setGender(genderMapper.toEnum(dto.gender()));
    }

    public UserDetailsResponseDTO update(Long id, UpdateUserDTO dto) {
        User entity = userRepository.findById(id).orElseThrow();
        mapper.updateEntity(dto, entity);

        updatePre(dto, entity);

        UserDetailsResponseDTO updatedDTO = mapper.toDetailsDTO(userRepository.save(entity));

        updatePos(updatedDTO, entity);

        return updatedDTO;
    }

    private void updatePos(IDTO dto, User entity) {
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}