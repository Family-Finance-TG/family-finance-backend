package com.fatecmogi.family_finance.family_invite.domain.service;

import com.fatecmogi.family_finance.auth.domain.util.AuthUserRecover;
import com.fatecmogi.family_finance.common.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import com.fatecmogi.family_finance.family.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.family_invite.application.dto.request.CreateFamilyInviteRequestDTO;
import com.fatecmogi.family_finance.family_invite.application.dto.response.FamilyInviteDetailsResponseDTO;
import com.fatecmogi.family_finance.family_invite.application.dto.response.FamilyInviteSummaryResponseDTO;
import com.fatecmogi.family_finance.family_invite.domain.mapper.FamilyInviteMapper;
import com.fatecmogi.family_finance.family_invite.infrastructure.entity.FamilyInvite;
import com.fatecmogi.family_finance.family_invite.infrastructure.entity.FamilyInviteStatusEnum;
import com.fatecmogi.family_finance.family_invite.infrastructure.repository.FamilyInviteRepository;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FamilyInviteService {
    private final FamilyInviteMapper mapper;
    private final FamilyInviteRepository familyInviteRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final AuthUserRecover authUserRecover;

    private void savePre() {}

    public FamilyInviteDetailsResponseDTO save(Long familyId, CreateFamilyInviteRequestDTO dto, JwtAuthenticationToken token) {
        User creator = authUserRecover.getByToken(token);
        User receiver = userRepository.findByInviteCode(dto.receiverInviteCode()).orElseThrow(
                () -> new FFResourceNotFoundException("Receiver not found")
        );
        Family family = familyRepository.findById(familyId).orElseThrow(
                () -> new FFResourceNotFoundException("Family not found")
        );

        FamilyInvite entity = new FamilyInvite();
        entity.setCreator(creator);
        entity.setReceiver(receiver);
        entity.setFamily(family);
        entity.setStatus(FamilyInviteStatusEnum.PENDING);

        familyInviteRepository.save(entity);

        return mapper.toDetailsDTO(entity);
    }

    private void savePos() {}

    public List<FamilyInviteSummaryResponseDTO> findAll(Long familyId, JwtAuthenticationToken token) {
        User user = authUserRecover.getByToken(token);
        Family family = familyRepository.findById(familyId).orElseThrow(
                () -> new FFResourceNotFoundException("Family not found")
        );

        return familyInviteRepository.findAllByFamilyId(family.getId())
                .stream()
                .map(mapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public FamilyInviteDetailsResponseDTO findById(Long familyId, Long inviteId, JwtAuthenticationToken token) {
        FamilyInvite entity = familyInviteRepository.findById(inviteId).orElseThrow(
                () -> new FFResourceNotFoundException("Invite not found")
        );

        return mapper.toDetailsDTO(entity);
    }

    public List<FamilyInviteSummaryResponseDTO> findAllByUserId(Long userId, JwtAuthenticationToken token) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new FFResourceNotFoundException("User not found")
        );

        return familyInviteRepository.findAllByReceiverId(user.getId())
                .stream()
                .map(mapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public void cancelInvite(Long familyId, Long inviteId, JwtAuthenticationToken token) {
        FamilyInvite entity = familyInviteRepository.findById(inviteId).orElseThrow(
                () -> new FFResourceNotFoundException("Invite not found")
        );

        entity.setStatus(FamilyInviteStatusEnum.CANCELED);

        familyInviteRepository.save(entity);
    }

    public FamilyInviteDetailsResponseDTO findByIdByUserId(Long userId, Long inviteId, JwtAuthenticationToken token) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new FFResourceNotFoundException("User not found")
        );

        FamilyInvite entity = familyInviteRepository.findById(inviteId).orElseThrow(
                () -> new FFResourceNotFoundException("Invite not found")
        );

        return mapper.toDetailsDTO(entity);
    }

    public void acceptInvite(Long userId, Long inviteId, JwtAuthenticationToken token) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new FFResourceNotFoundException("User not found")
        );

        FamilyInvite entity = familyInviteRepository.findById(inviteId).orElseThrow(
                () -> new FFResourceNotFoundException("Invite not found")
        );

        entity.setStatus(FamilyInviteStatusEnum.ACCEPTED);

        Family family = entity.getFamily();
        family.getMembers().add(user);
        familyRepository.save(family);
        familyInviteRepository.save(entity);
    }

    public void rejectInvite(Long userId, Long inviteId, JwtAuthenticationToken token) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new FFResourceNotFoundException("User not found")
        );

        FamilyInvite entity = familyInviteRepository.findById(inviteId).orElseThrow(
                () -> new FFResourceNotFoundException("Invite not found")
        );

        entity.setStatus(FamilyInviteStatusEnum.REJECTED);

        familyInviteRepository.save(entity);
    }

}
