package com.fatecmogi.family_finance.domain.mapper.family_debt;

import com.fatecmogi.family_finance.application.dto.family_debt.FamilyDebtDTO;
import com.fatecmogi.family_finance.domain.mapper.IMapper;
import com.fatecmogi.family_finance.domain.mapper.family.FamilyMapper;
import com.fatecmogi.family_finance.domain.mapper.user.UserMapper;
import com.fatecmogi.family_finance.infrastructure.entity.family_debt.FamilyDebt;
import org.springframework.stereotype.Component;

@Component
public class FamilyDebtMapper implements IMapper<FamilyDebt, FamilyDebtDTO> {
    private final UserMapper userMapper;
    private final FamilyMapper familyMapper;

    public FamilyDebtMapper(UserMapper userMapper, FamilyMapper familyMapper) {
        this.userMapper = userMapper;
        this.familyMapper = familyMapper;
    }

    @Override
    public FamilyDebtDTO toDTO(FamilyDebt entity) {
        return new FamilyDebtDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getValue(),
                familyMapper.toDTO(entity.getFamily()),
                userMapper.toDTO(entity.getCreator()),
                userMapper.toDTO(entity.getResponsible()),
                entity.getDueDate(),
                entity.getPaymentStatus()
        );
    }

    @Override
    public FamilyDebt toEntity(FamilyDebtDTO dto) {
        FamilyDebt entity = new FamilyDebt();
        updateEntity(dto, entity);
        return entity;
    }

    @Override
    public void updateEntity(FamilyDebtDTO dto, FamilyDebt entity) {
        entity.setId(dto.id());
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setValue(dto.value());
        entity.setDueDate(dto.dueDate());
        entity.setPaymentStatus(dto.paymentStatus());
    }

}
