package com.fatecmogi.family_finance.domain.service.family;

import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.application.dto.family.request.CreateFamilyDTO;
import com.fatecmogi.family_finance.application.dto.family.request.UpdateFamilyDTO;
import com.fatecmogi.family_finance.application.dto.family.response.FamilyDetailsResponseDTO;
import com.fatecmogi.family_finance.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.domain.mapper.family.FamilyMapper;
import com.fatecmogi.family_finance.domain.mapper.user.UserMapper;
import com.fatecmogi.family_finance.domain.util.AuthUserRecover;
import com.fatecmogi.family_finance.infrastructure.entity.auth.RoleEnum;
import com.fatecmogi.family_finance.infrastructure.entity.family.Family;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import com.fatecmogi.family_finance.infrastructure.repository.family.FamilyRepository;
import com.fatecmogi.family_finance.infrastructure.repository.auth.RoleRepository;
import com.fatecmogi.family_finance.infrastructure.repository.user.UserRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final FamilyMapper familyMapper;
    private final AuthUserRecover authUserRecover;
    private final RoleRepository roleRepository;

    public FamilyService(FamilyRepository familyRepository, UserRepository userRepository, FamilyMapper familyMapper, UserMapper userMapper, AuthUserRecover authUserRecover, RoleRepository roleRepository) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
        this.familyMapper = familyMapper;
        this.authUserRecover = authUserRecover;
        this.roleRepository = roleRepository;
    }

    private void savePre(CreateFamilyDTO dto, Family entity) {
        entity.setDebts(new HashSet<>());
    }

    public FamilyDetailsResponseDTO save(CreateFamilyDTO dto, JwtAuthenticationToken token) {
        User creator = authUserRecover.getByToken(token);
        Family entity = familyMapper.toEntity(dto);
        entity.setMembers(Set.of(creator));

        savePre(dto, entity);
        FamilyDetailsResponseDTO savedDTO = familyMapper.toDetailsDTO(familyRepository.save(entity));
        savePos(savedDTO, entity, creator);

        return savedDTO;
    }

    private void savePos(FamilyDetailsResponseDTO dto, Family entity, User creator) {
        creator.getRoles().add(roleRepository.findByValue(RoleEnum.ADMIN.getValue()));
        userRepository.save(creator);
    }

    public FamilyDetailsResponseDTO findById(Long id) throws FFResourceNotFoundException {
        Family family = familyRepository.findByIdWithMembers(id)
                .orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        return familyMapper.toDetailsDTO(family);
    }

    private void addMemberPre(Family family, User newMember) {
    }

    public FamilyDetailsResponseDTO addMember(Long familyId, Long userId, JwtAuthenticationToken token) {
        User authUser = authUserRecover.getByToken(token);
        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new FFResourceNotFoundException("User not found"));

        addMemberPre(family, user);
        family.getMembers().add(user);
        FamilyDetailsResponseDTO savedFamilyDTO = familyMapper.toDetailsDTO(familyRepository.save(family));

        userRepository.save(user);
        addMemberPos(savedFamilyDTO, family, user);

        return savedFamilyDTO;
    }

    private void addMemberPos(FamilyDetailsResponseDTO dto, Family entity, User newMember) {
    }

    public void removeMember(Long familyId, Long userId, JwtAuthenticationToken token) {
        User authUser = authUserRecover.getByToken(token);
        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new FFResourceNotFoundException("User not found"));

        family.getMembers().remove(user);
        FamilyDTO savedFamilyDTO = familyMapper.toDTO(familyRepository.save(family));

        family.getMembers().remove(user);
        userRepository.save(user);
    }

    private void updatePre(UpdateFamilyDTO dto, Family family) {
    }

    public FamilyDetailsResponseDTO update(Long familyId, UpdateFamilyDTO dto, JwtAuthenticationToken token) {
        User authUser = authUserRecover.getByToken(token);
        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));

        updatePre(dto, family);
        family.setName(dto.name());
        FamilyDetailsResponseDTO savedFamilyDTO = familyMapper.toDetailsDTO(familyRepository.save(family));
        updatePos(savedFamilyDTO, family);

        return savedFamilyDTO;
    }

    private void updatePos(FamilyDetailsResponseDTO dto, Family entity) {
    }
}