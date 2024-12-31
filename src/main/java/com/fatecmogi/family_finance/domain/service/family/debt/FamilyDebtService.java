package com.fatecmogi.family_finance.domain.service.family.debt;

import com.fatecmogi.family_finance.application.dto.family.debt.request.CreateFamilyDebtDTO;
import com.fatecmogi.family_finance.application.dto.family.debt.request.UpdatePaymentStatusDTO;
import com.fatecmogi.family_finance.application.dto.family.debt.request.UpdateResponsibleDTO;
import com.fatecmogi.family_finance.application.dto.family.debt.response.FamilyDebtDetailsResponseDTO;
import com.fatecmogi.family_finance.domain.mapper.family.debt.FamilyDebtMapper;
import com.fatecmogi.family_finance.infrastructure.entity.family.Family;
import com.fatecmogi.family_finance.infrastructure.entity.family.debt.FamilyDebt;
import com.fatecmogi.family_finance.infrastructure.entity.family.debt.payment_status.PaymentStatusEnum;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import com.fatecmogi.family_finance.infrastructure.repository.family.FamilyDebtRepository;
import com.fatecmogi.family_finance.infrastructure.repository.family.FamilyRepository;
import com.fatecmogi.family_finance.infrastructure.repository.user.UserRepository;
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
