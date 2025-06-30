package com.fatecmogi.family_finance.family_debt.domain.service;

import com.fatecmogi.family_finance.auth.domain.util.PermissionValidator;
import com.fatecmogi.family_finance.auth.infrastructure.entity.PermissionEnum;
import com.fatecmogi.family_finance.common.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.family_debt.application.dto.request.*;
import com.fatecmogi.family_finance.family_debt.application.dto.response.FamilyDebtDetailsResponseDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.response.FamilyDebtSummaryResponseDTO;
import com.fatecmogi.family_finance.family_debt.domain.mapper.FamilyDebtMapper;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import com.fatecmogi.family_finance.family_debt.infrastructure.entity.FamilyDebt;
import com.fatecmogi.family_finance.family_debt.infrastructure.entity.PaymentStatusEnum;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.family_debt.infrastructure.repository.FamilyDebtRepository;
import com.fatecmogi.family_finance.family.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FamilyDebtService {
    private final FamilyDebtRepository familyDebtRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final FamilyDebtMapper familyDebtMapper;
    private final PermissionValidator permissionValidator;

    private void savePre(CreateFamilyDebtDTO dto, FamilyDebt entity, Family family) {
        entity.setFamily(family);
    }

    public FamilyDebtDetailsResponseDTO save(long familyId, CreateFamilyDebtDTO dto) {
        User creator = userRepository.findById(dto.creatorId()).orElseThrow(() -> new FFResourceNotFoundException("Usuário criador não encontrado"));
        permissionValidator.hasPermissionOrThrow(creator, PermissionEnum.DEBT_ADD);

        FamilyDebt entity = familyDebtMapper.toEntity(dto);
        Family family = familyRepository.findById(familyId).orElseThrow();

        entity.setCreator(creator);
        entity.setResponsible(userRepository.findById(dto.responsibleId()).orElseThrow());

        savePre(dto, entity, family);
        FamilyDebtDetailsResponseDTO savedDTO = familyDebtMapper.toDetailsDTO(familyDebtRepository.save(entity));
        savePos(savedDTO, entity);

        return savedDTO;
    }

    private void savePos(FamilyDebtDetailsResponseDTO dto, FamilyDebt entity) {

    }

    public List<FamilyDebtSummaryResponseDTO> findAll(long familyId) {
        return familyDebtRepository.findAllByFamilyId(familyId).stream()
                .map(familyDebtMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public FamilyDebtDetailsResponseDTO findById(long familyId, long familyDebtId) {
        return familyDebtMapper.toDetailsDTO(familyDebtRepository.findById(familyDebtId).orElseThrow());
    }

    public FamilyDebtDetailsResponseDTO updatePaymentStatus(long familyId, long familyDebtId, UpdatePaymentStatusDTO dto) {
        FamilyDebt familyDebt = familyDebtRepository.findById(familyDebtId).orElseThrow();
        familyDebt.setPaymentStatus(PaymentStatusEnum.fromValue(dto.value()));
        familyDebtRepository.save(familyDebt);
        return familyDebtMapper.toDetailsDTO(familyDebt);
    }

    public FamilyDebtDetailsResponseDTO updateResponsible(long familyId, long familyDebtId, UpdateResponsibleDTO userDTO) {
        FamilyDebt familyDebt = familyDebtRepository.findById(familyDebtId).orElseThrow();
        User responsible = userRepository.findById(userDTO.id()).orElseThrow();
        familyDebt.setResponsible(responsible);
        familyDebtRepository.save(familyDebt);
        return familyDebtMapper.toDetailsDTO(familyDebt);
    };
    public List<FamilyDebtDetailsResponseDTO> saveRecurringDebts(long familyId, CreateRecurringDebtsDTO dto) {
        User creator = userRepository.findById(dto.creatorId()).orElseThrow(() -> new FFResourceNotFoundException("Usuário criador não encontrado"));
        permissionValidator.hasPermissionOrThrow(creator, PermissionEnum.DEBT_ADD);

        Family family = familyRepository.findById(familyId).orElseThrow(() -> new FFResourceNotFoundException("Família não encontrada"));
        User responsible = userRepository.findById(dto.responsibleId()).orElseThrow(() -> new FFResourceNotFoundException("Usuário responsável não encontrado"));

        List<FamilyDebt> debts = new ArrayList<>();
        for (int i = 0; i < dto.months(); i++) {
            FamilyDebt debt = new FamilyDebt();
            debt.setTitle(dto.title());
            debt.setDescription(dto.description());
            debt.setValue(dto.value());
            debt.setCreator(creator);
            debt.setResponsible(responsible);
            debt.setPaymentStatus(PaymentStatusEnum.fromValue(dto.paymentStatus()));
            debt.setFamily(family);
            debt.setDueDate(dto.firstDueDate().plusMonths(i));
            debts.add(debt);
        }

        familyDebtRepository.saveAll(debts);
        return debts.stream().map(familyDebtMapper::toDetailsDTO).toList();
    }
    // FamilyDebtService.java
    public FamilyDebtDetailsResponseDTO updateDebt(JwtAuthenticationToken token, long familyId, long debtId, UpdateFamilyDebtDTO dto) {
        permissionValidator.hasPermissionOrThrow(token, PermissionEnum.DEBT_EDIT);

        FamilyDebt debt = familyDebtRepository.findById(debtId).orElseThrow(() -> new RuntimeException("Dívida não encontrada"));

        if (!debt.getFamily().getId().equals(familyId)) {
            throw new RuntimeException("Dívida não encontrada.");
        }

        debt.setTitle(dto.title());
        debt.setDescription(dto.description());
        debt.setValue(dto.value());
        debt.setDueDate(dto.dueDate());

        if (dto.responsibleId() != null) {
            User responsible = userRepository.findById(dto.responsibleId()).orElseThrow();
            debt.setResponsible(responsible);
        }

        familyDebtRepository.save(debt);
        return familyDebtMapper.toDetailsDTO(debt);
    }

    public void delete(JwtAuthenticationToken token, long familyId, long familyDebtId) {
        permissionValidator.hasPermissionOrThrow(token, PermissionEnum.DEBT_DELETE);
        FamilyDebt debt = familyDebtRepository.findById(familyDebtId)
                .orElseThrow(() -> new RuntimeException("Dívida não encontrada"));

        if (!debt.getFamily().getId().equals(familyId)) {
            throw new RuntimeException("Dívida não encontrada.");
        }

        familyDebtRepository.delete(debt);
    }


}
