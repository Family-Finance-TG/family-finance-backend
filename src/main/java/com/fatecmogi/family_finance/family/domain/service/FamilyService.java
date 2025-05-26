package com.fatecmogi.family_finance.family.domain.service;

import com.fatecmogi.family_finance.auth.infrastructure.entity.Role;
import com.fatecmogi.family_finance.family.application.dto.request.CreateFamilyDTO;
import com.fatecmogi.family_finance.family.application.dto.request.UpdateFamilyDTO;
import com.fatecmogi.family_finance.family.application.dto.response.FamilyDetailsResponseDTO;
import com.fatecmogi.family_finance.common.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.family.application.dto.response.FamilySummaryResponseDTO;
import com.fatecmogi.family_finance.family.domain.mapper.FamilyMapper;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryWithPermissionsDTO;
import com.fatecmogi.family_finance.user.domain.mapper.UserMapper;
import com.fatecmogi.family_finance.auth.domain.util.AuthUserRecover;
import com.fatecmogi.family_finance.auth.infrastructure.entity.RoleEnum;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.auth.infrastructure.repository.RoleRepository;
import com.fatecmogi.family_finance.family.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final FamilyMapper familyMapper;
    private final AuthUserRecover authUserRecover;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public FamilyService(FamilyRepository familyRepository, UserRepository userRepository, FamilyMapper familyMapper, UserMapper userMapper, AuthUserRecover authUserRecover, RoleRepository roleRepository) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
        this.familyMapper = familyMapper;
        this.userMapper = userMapper;
        this.authUserRecover = authUserRecover;
        this.roleRepository = roleRepository;

    }

    private void savePre(CreateFamilyDTO dto, Family entity) {

    }

    public FamilyDetailsResponseDTO save(CreateFamilyDTO dto, JwtAuthenticationToken token) {
        User creator = authUserRecover.getByToken(token);
        Family entity = familyMapper.toEntity(dto);
        entity.setMembers(Set.of(creator));
        entity.setCreator(creator); // 👈 ESSENCIAL

        savePre(dto, entity);
        FamilyDetailsResponseDTO savedDTO = familyMapper.toDetailsDTO(familyRepository.save(entity));
        savePos(savedDTO, entity, creator);

        return savedDTO;
    }


    private void savePos(FamilyDetailsResponseDTO dto, Family entity, User creator) {
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(roleRepository.findByValue(RoleEnum.ADMIN.getValue()));
        allRoles.add(roleRepository.findByValue(RoleEnum.CAN_ADD.getValue()));
        allRoles.add(roleRepository.findByValue(RoleEnum.CAN_EDIT.getValue()));
        allRoles.add(roleRepository.findByValue(RoleEnum.CAN_DELETE.getValue()));
        allRoles.add(roleRepository.findByValue(RoleEnum.CAN_INVITE.getValue()));
        allRoles.add(roleRepository.findByValue(RoleEnum.CAN_REMOVE.getValue()));

        creator.getRoles().addAll(allRoles);
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
        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new FFResourceNotFoundException("User not found"));

        user.setFamily(null);
        family.getMembers().remove(user);

        family.getMembers().remove(user);
        return familyMapper.toDetailsDTO(familyRepository.save(family));
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

    public FamilyDetailsResponseDTO updateMemberPermissions(Long familyId, Long memberId, Map<String, Boolean> permissions) {
        // Busca o usuário com vínculo de família
        User member = userRepository.findByIdWithFamily(memberId)
                .orElseThrow(() -> new FFResourceNotFoundException("Usuário não encontrado"));
        // Verificação de vínculo com a família
        if (member.getFamily() == null || !member.getFamily().getId().equals(familyId)) {
            throw new FFResourceNotFoundException("Usuário não pertence à família");
        }
        member.getRoles().clear();

        permissions.forEach((key, value) -> {
            if (Boolean.TRUE.equals(value)) {
                Role role = null;
                switch (key) {
                    case "canAdd" -> role = roleRepository.findByValue("CAN_ADD");
                    case "canEdit" -> role = roleRepository.findByValue("CAN_EDIT");
                    case "canDelete" -> role = roleRepository.findByValue("CAN_DELETE");
                    case "canInvite" -> role = roleRepository.findByValue("CAN_INVITE");
                    case "canRemove" -> role = roleRepository.findByValue("CAN_REMOVE");
                }

                if (role != null) {
                    member.getRoles().add(role);
                } else {
                    throw new FFResourceNotFoundException("Permissão inválida ou não encontrada: " + key);
                }

            }
        });

        userRepository.save(member);

        return findByIdWithMembers(familyId);
    }
    private void updatePos(FamilyDetailsResponseDTO dto, Family entity) {
    }
}