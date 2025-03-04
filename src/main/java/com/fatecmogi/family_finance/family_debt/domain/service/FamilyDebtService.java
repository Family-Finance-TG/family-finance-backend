package com.fatecmogi.family_finance.family_debt.domain.service;

import com.fatecmogi.family_finance.family_debt.application.dto.request.CreateFamilyDebtDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.request.UpdatePaymentStatusDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.request.UpdateResponsibleDTO;
import com.fatecmogi.family_finance.family_debt.application.dto.response.FamilyDebtDetailsResponseDTO;
import com.fatecmogi.family_finance.family_debt.domain.mapper.FamilyDebtMapper;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import com.fatecmogi.family_finance.family_debt.infrastructure.entity.FamilyDebt;
import com.fatecmogi.family_finance.family_debt.infrastructure.entity.PaymentStatusEnum;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.family_debt.infrastructure.repository.FamilyDebtRepository;
import com.fatecmogi.family_finance.family.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    private void savePre(CreateFamilyDebtDTO dto, FamilyDebt entity) {

    }

    public FamilyDebtDetailsResponseDTO save(long familyId, CreateFamilyDebtDTO dto) {
        FamilyDebt entity = familyDebtMapper.toEntity(dto);
        Family family = familyRepository.findById(familyId).orElseThrow();

        entity.setCreator(userRepository.findById(dto.creatorId()).orElseThrow());
        entity.setResponsible(userRepository.findById(dto.responsibleId()).orElseThrow());

        savePre(dto, entity);
        FamilyDebtDetailsResponseDTO savedDTO = familyDebtMapper.toDetailsDTO(familyDebtRepository.save(entity));
        family.getDebts().add(entity);
        familyRepository.save(family);
        savePos(savedDTO, entity);

        return savedDTO;
    }

    private void savePos(FamilyDebtDetailsResponseDTO dto, FamilyDebt entity) {

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
}
