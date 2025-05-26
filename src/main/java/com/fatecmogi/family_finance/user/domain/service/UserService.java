package com.fatecmogi.family_finance.user.domain.service;

import com.fatecmogi.family_finance.common.application.dto.IDTO;
import com.fatecmogi.family_finance.common.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import com.fatecmogi.family_finance.family.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.user.application.dto.request.UpdateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;
import com.fatecmogi.family_finance.user.domain.mapper.UserMapper;
import com.fatecmogi.family_finance.user.domain.mapper.GenderMapper;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final UserMapper mapper;
    private final GenderMapper genderMapper;

    public UserService(UserRepository userRepository, FamilyRepository familyRepository, UserMapper mapper, GenderMapper genderMapper) {
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
        this.mapper = mapper;
        this.genderMapper = genderMapper;
    }

    public List<UserSummaryResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(mapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public UserDetailsResponseDTO findById(Long id) {
        return mapper.toDetailsDTO(
                userRepository.findByIdAndActiveTrue(id)
                        .orElseThrow(() -> new FFResourceNotFoundException("Usuário não encontrado ou inativo"))
        );
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

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new FFResourceNotFoundException("Usuário não encontrado"));

        user.setActive(false); // inativa a conta
        userRepository.save(user);
    }
    public UserDetailsResponseDTO leaveFamily(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new FFResourceNotFoundException("User not found")
        );

        if (user.getFamily() == null) {
            throw new FFResourceNotFoundException("User does not belong to a family");
        }

        Family family = user.getFamily();
        family.getMembers().remove(user);
        user.setFamily(null);

        userRepository.save(user);
        familyRepository.save(family);

        return mapper.toDetailsDTO(user);
    }
}