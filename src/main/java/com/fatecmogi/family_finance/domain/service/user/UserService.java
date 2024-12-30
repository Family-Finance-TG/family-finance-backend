package com.fatecmogi.family_finance.domain.service.user;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.user.request.UpdateUserDTO;
import com.fatecmogi.family_finance.application.dto.user.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.application.dto.user.response.UserSummaryResponseDTO;
import com.fatecmogi.family_finance.domain.mapper.user.UserMapper;
import com.fatecmogi.family_finance.domain.mapper.user.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import com.fatecmogi.family_finance.infrastructure.repository.user.UserRepository;
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