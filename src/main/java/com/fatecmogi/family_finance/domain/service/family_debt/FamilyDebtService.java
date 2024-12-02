package com.fatecmogi.family_finance.domain.service.family_debt;

import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.application.dto.family_debt.FamilyDebtDTO;
import com.fatecmogi.family_finance.application.dto.payment_status.PaymentStatusDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.domain.exception.FFResourceNotFoundException;
import com.fatecmogi.family_finance.domain.mapper.family_debt.FamilyDebtMapper;
import com.fatecmogi.family_finance.infrastructure.entity.Family;
import com.fatecmogi.family_finance.infrastructure.entity.User;
import com.fatecmogi.family_finance.infrastructure.entity.family_debt.FamilyDebt;
import com.fatecmogi.family_finance.infrastructure.repository.FamilyDebtRepository;
import com.fatecmogi.family_finance.infrastructure.repository.FamilyRepository;
import com.fatecmogi.family_finance.infrastructure.repository.PaymentStatusRepository;
import com.fatecmogi.family_finance.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FamilyDebtService {
    private final FamilyDebtRepository familyDebtRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final FamilyDebtMapper familyDebtMapper;

    public FamilyDebtService(FamilyDebtRepository familyDebtRepository, FamilyRepository familyRepository, UserRepository userRepository, PaymentStatusRepository paymentStatusRepository, FamilyDebtMapper familyDebtMapper) {
        this.familyDebtRepository = familyDebtRepository;
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.familyDebtMapper = familyDebtMapper;
    }

    private void savePre(FamilyDebtDTO dto, FamilyDebt entity) {

    }

    public FamilyDebtDTO save(long familyId, FamilyDebtDTO dto) {
        FamilyDebt entity = familyDebtMapper.toEntity(dto);
        entity.setFamily(familyRepository.findById(familyId).orElseThrow());
        entity.setCreator(userRepository.findById(dto.creator().id()).orElseThrow());
        entity.setResponsible(userRepository.findById(dto.responsible().id()).orElseThrow());

        savePre(dto, entity);
        FamilyDebtDTO savedDTO = familyDebtMapper.toDTO(familyDebtRepository.save(entity));
        savePos(savedDTO, entity);

        return savedDTO;
    }

    private void savePos(FamilyDebtDTO dto, FamilyDebt entity) {

    }

    public FamilyDebtDTO updatePaymentStatus(long familyId, long familyDebtId, PaymentStatusDTO dto) {
        Family family = familyRepository.findById(familyId).orElseThrow();
        FamilyDebt familyDebt = familyDebtRepository.findById(familyDebtId).orElseThrow();
        familyDebt.setPaymentStatus(paymentStatusRepository.findByValue(dto.value()).orElseThrow());
        familyDebtRepository.save(familyDebt);
        return familyDebtMapper.toDTO(familyDebt);
    }

    public FamilyDebtDTO updateResponsible(long familyId, long familyDebtId, UserDTO userDTO) {
        Family family = familyRepository.findById(familyId).orElseThrow();
        FamilyDebt familyDebt = familyDebtRepository.findById(familyDebtId).orElseThrow();
        User responsible = userRepository.findById(userDTO.id()).orElseThrow();
        familyDebt.setResponsible(responsible);
        familyDebtRepository.save(familyDebt);
        return familyDebtMapper.toDTO(familyDebt);
    };
}
