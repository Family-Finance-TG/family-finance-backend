package com.fatecmogi.family_finance.family.domain.service;

import com.fatecmogi.family_finance.auth.domain.util.PermissionValidator;
import com.fatecmogi.family_finance.auth.infrastructure.entity.Permission;
import com.fatecmogi.family_finance.family.application.dto.request.CreateFamilyDTO;
import com.fatecmogi.family_finance.family.application.dto.request.UpdateFamilyDTO;
import com.fatecmogi.family_finance.family.application.dto.response.FamilyDetailsResponseDTO;
import com.fatecmogi.family_finance.common.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.family.application.dto.response.FamilySummaryResponseDTO;
import com.fatecmogi.family_finance.family.domain.mapper.FamilyMapper;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryWithPermissionsDTO;
import com.fatecmogi.family_finance.user.domain.mapper.UserMapper;
import com.fatecmogi.family_finance.auth.domain.util.AuthUserRecover;
import com.fatecmogi.family_finance.auth.infrastructure.entity.PermissionEnum;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.auth.infrastructure.repository.PermissionRepository;
import com.fatecmogi.family_finance.family.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final FamilyMapper familyMapper;
    private final AuthUserRecover authUserRecover;
    private final PermissionRepository permissionRepository;
    private final UserMapper userMapper;
    private final PermissionValidator permissionValidator;

    private void savePre(CreateFamilyDTO dto, Family entity) {

    }

    public FamilyDetailsResponseDTO save(CreateFamilyDTO dto, JwtAuthenticationToken token) {
        User creator = authUserRecover.getByToken(token);
        Family entity = familyMapper.toEntity(dto);
        entity.setMembers(Set.of(creator));
        entity.setCreator(creator);

        savePre(dto, entity);
        FamilyDetailsResponseDTO savedDTO = familyMapper.toDetailsDTO(familyRepository.save(entity));
        savePos(savedDTO, entity, creator);

        return savedDTO;
    }


    private void savePos(FamilyDetailsResponseDTO dto, Family entity, User creator) {
        Set<Permission> allPermissions = new HashSet<>(permissionRepository.findAll());
        creator.setPermissions(allPermissions);
        creator.setFamily(entity);
        userRepository.save(creator);
    }

    public FamilySummaryResponseDTO findById(Long id) throws FFResourceNotFoundException {
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        return familyMapper.toSummaryDTO(family);
    }

//    private void addMemberPre(Family family, User newMember) {
//    }
//
//    public FamilyDetailsResponseDTO addMember(Long familyId, Long userId, JwtAuthenticationToken token) {
//        User authUser = authUserRecover.getByToken(token);
//        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
//        User user = userRepository.findById(userId).orElseThrow(() -> new FFResourceNotFoundException("User not found"));
//
//        addMemberPre(family, user);
//        family.getMembers().add(user);
//        FamilyDetailsResponseDTO savedFamilyDTO = familyMapper.toDetailsDTO(familyRepository.save(family));
//
//        userRepository.save(user);
//        addMemberPos(savedFamilyDTO, family, user);
//
//        return savedFamilyDTO;
//    }
//
//    private void addMemberPos(FamilyDetailsResponseDTO dto, Family entity, User newMember) {
//    }

    //public FamilyDetailsResponseDTO findByIdWithMembers(Long id) {
     //   Family family = familyRepository.findByIdWithMembers(id)
       //         .orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        //return familyMapper.toDetailsDTO(family);
    //}
    public FamilyDetailsResponseDTO findByIdWithMembers(Long id) {
        Family family = familyRepository.findByIdWithMembers(id)
                .orElseThrow(() -> new FFResourceNotFoundException("Family not found"));

        Set<UserSummaryWithPermissionsDTO> memberDTOs = family.getMembers().stream()
                .map(userMapper::toSummaryWithPermissionsDTO)
                .collect(Collectors.toSet());

        return new FamilyDetailsResponseDTO(family.getId(), family.getName(), family.getCreator().getId(),memberDTOs);
    }
    public FamilyDetailsResponseDTO removeMember(Long familyId, Long userId, JwtAuthenticationToken token) {
        User authUser = authUserRecover.getByToken(token);
        permissionValidator.hasPermissionOrThrow(authUser, PermissionEnum.MEMBER_REMOVE);

        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Família não encontrada"));
        User user = userRepository.findById(userId).orElseThrow(() -> new FFResourceNotFoundException("Usuário não encontrado"));

        user.setFamily(null);
        family.getMembers().remove(user);

        family.getMembers().remove(user);
        return familyMapper.toDetailsDTO(familyRepository.save(family));
    }

    private void updatePre(UpdateFamilyDTO dto, Family family) {
    }

    public FamilyDetailsResponseDTO update(Long familyId, UpdateFamilyDTO dto, JwtAuthenticationToken token) {
        User authUser = authUserRecover.getByToken(token);
        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Família não encontrada"));

        updatePre(dto, family);
        family.setName(dto.name());
        FamilyDetailsResponseDTO savedFamilyDTO = familyMapper.toDetailsDTO(familyRepository.save(family));
        updatePos(savedFamilyDTO, family);

        return savedFamilyDTO;
    }

    public FamilyDetailsResponseDTO updateMemberPermissions(Long familyId, Long memberId, Map<String, Boolean> permissions) {
        User member = userRepository.findByIdWithFamily(memberId)
                .orElseThrow(() -> new FFResourceNotFoundException("Usuário não encontrado"));

        if (member.getFamily() == null || !member.getFamily().getId().equals(familyId)) {
            throw new FFResourceNotFoundException("Usuário não encontrado");
        }
        member.getPermissions().clear();

        permissions.forEach((key, value) -> {
            if (Boolean.TRUE.equals(value)) {
                Permission permission = permissionRepository.findByValue(key);

                if (permission == null) {
                    throw new FFResourceNotFoundException("Permissão não encontrada: " + key);
                }

                member.getPermissions().add(permission);
            }
        });

        userRepository.save(member);

        return findByIdWithMembers(familyId);
    }
    private void updatePos(FamilyDetailsResponseDTO dto, Family entity) {
    }
}