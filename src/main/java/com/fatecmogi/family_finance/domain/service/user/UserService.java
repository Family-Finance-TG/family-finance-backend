package com.fatecmogi.family_finance.domain.service.user;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.domain.mapper.user.UserMapper;
import com.fatecmogi.family_finance.infrastructure.entity.Gender;
import com.fatecmogi.family_finance.infrastructure.entity.User;
import com.fatecmogi.family_finance.infrastructure.repository.GenderRepository;
import com.fatecmogi.family_finance.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final GenderRepository genderRepository;

    public UserService(UserRepository userRepository, UserMapper mapper, GenderRepository genderRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.genderRepository = genderRepository;
    }

    private void savePre(UserDTO dto, User entity) {
        entity.setActive(true);
        Gender gender = genderRepository.findById(dto.gender().id()).orElseThrow();
        entity.setGender(gender);
    }

    public UserDTO save(UserDTO dto) {
        User entity = mapper.toEntity(dto);

        savePre(dto, entity);
        UserDTO savedDTO = mapper.toDTO(userRepository.save(entity));
        savePos(savedDTO, entity);

        return savedDTO;
    }

    private void savePos(IDTO dto, User entity) {

    }

    public UserDTO findById(Long id) {
        return mapper.toDTO(userRepository.findById(id).orElseThrow());
    }

    private void updatePre(UserDTO dto, User entity) {
        Gender gender = genderRepository.findById(dto.gender().id()).orElseThrow();
        entity.setGender(gender);
    }

    public UserDTO update(Long id, UserDTO dto) {
        User entity = userRepository.findById(id).orElseThrow();
        mapper.updateEntity(dto, entity);

        updatePre(dto, entity);

        UserDTO updatedDTO = mapper.toDTO(userRepository.save(entity));

        updatePos(updatedDTO, entity);

        return updatedDTO;
    }

    private void updatePos(IDTO dto, User entity) {
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}