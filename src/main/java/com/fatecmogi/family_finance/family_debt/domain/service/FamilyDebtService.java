package com.fatecmogi.family_finance.family_debt.domain.service;

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
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyDebtService {
    private final FamilyDebtRepository familyDebtRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final FamilyDebtMapper familyDebtMapper;

    public FamilyDebtService(FamilyDebtRepository familyDebtRepository, FamilyRepository familyRepository, UserRepository userRepository, FamilyDebtMapper familyDebtMapper) {
        this.familyDebtRepository = familyDebtRepository;
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
        this.familyDebtMapper = familyDebtMapper;
    }

    private void savePre(CreateFamilyDebtDTO dto, FamilyDebt entity, Family family) {
        entity.setFamily(family);
    }

    public FamilyDebtDetailsResponseDTO save(long familyId, CreateFamilyDebtDTO dto) {
        FamilyDebt entity = familyDebtMapper.toEntity(dto);
        Family family = familyRepository.findById(familyId).orElseThrow();

        entity.setCreator(userRepository.findById(dto.creatorId()).orElseThrow());
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
        Family family = familyRepository.findById(familyId).orElseThrow();
        User creator = userRepository.findById(dto.creatorId()).orElseThrow();
        User responsible = userRepository.findById(dto.responsibleId()).orElseThrow();

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
    public FamilyDebtDetailsResponseDTO updateDebt(long familyId, long debtId, UpdateFamilyDebtDTO dto) {
        FamilyDebt debt = familyDebtRepository.findById(debtId).orElseThrow();

        if (!debt.getFamily().getId().equals(familyId)) {
            throw new RuntimeException("Dívida não pertence à família.");
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
    public void delete(long familyId, long familyDebtId) {
        FamilyDebt debt = familyDebtRepository.findById(familyDebtId)
                .orElseThrow(() -> new RuntimeException("Dívida não encontrada"));

        if (!debt.getFamily().getId().equals(familyId)) {
            throw new RuntimeException("Dívida não pertence à família.");
        }

        familyDebtRepository.delete(debt);
    }


}
