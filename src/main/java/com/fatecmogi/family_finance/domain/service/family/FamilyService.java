package com.fatecmogi.family_finance.domain.service.family;

import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.domain.mapper.family.FamilyMapper;
import com.fatecmogi.family_finance.domain.mapper.user.UserMapper;
import com.fatecmogi.family_finance.domain.util.AuthUserRecover;
import com.fatecmogi.family_finance.infrastructure.entity.Family;
import com.fatecmogi.family_finance.infrastructure.entity.Role;
import com.fatecmogi.family_finance.infrastructure.entity.User;
import com.fatecmogi.family_finance.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.infrastructure.repository.RoleRepository;
import com.fatecmogi.family_finance.infrastructure.repository.UserRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

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

    private void savePre(FamilyDTO dto, Family entity) {
        
    }

    public FamilyDTO save(FamilyDTO dto, JwtAuthenticationToken token) {
        User creator = authUserRecover.getByToken(token);
        Family entity = familyMapper.toEntity(dto, creator);


        savePre(dto, entity);
        FamilyDTO savedDTO = familyMapper.toDTO(familyRepository.save(entity));
        savePos(savedDTO, entity, creator);

        return savedDTO;
    }

    private void savePos(FamilyDTO dto, Family entity, User creator) {
        creator.setFamily(entity);
        creator.getRoles().add(roleRepository.findByValue(Role.Values.ADMIN.getValue()));
        userRepository.save(creator);
    }

    public FamilyDTO findById(Long id) throws FFResourceNotFoundException {
        Family family = familyRepository.findById(id).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        family.setMembers(userRepository.findByFamilyId(id).orElseThrow(() -> new FFResourceNotFoundException("Members not found")));

        return familyMapper.toDTO(family);
    }

    private void addMemberPre(Family family, User newMember) {
    }

    public FamilyDTO addMember(Long familyId, Long userId, JwtAuthenticationToken token) {
        User authUser = authUserRecover.getByToken(token);
        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new FFResourceNotFoundException("User not found"));

        addMemberPre(family, user);
        family.getMembers().add(user);
        FamilyDTO savedFamilyDTO = familyMapper.toDTO(familyRepository.save(family));

        user.setFamily(family);
        userRepository.save(user);
        addMemberPos(savedFamilyDTO, family, user);

        return savedFamilyDTO;
    }

    private void addMemberPos(FamilyDTO dto, Family entity, User newMember) {
    }

    public void removeMember(Long familyId, Long userId, JwtAuthenticationToken token) {
        User authUser = authUserRecover.getByToken(token);
        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Family not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new FFResourceNotFoundException("User not found"));

        family.getMembers().remove(user);
        FamilyDTO savedFamilyDTO = familyMapper.toDTO(familyRepository.save(family));

        user.setFamily(null);
        userRepository.save(user);
    }
}