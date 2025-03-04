package com.fatecmogi.family_finance.family_debt.infrastructure.repository;

import com.fatecmogi.family_finance.family_debt.infrastructure.entity.FamilyDebt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyDebtRepository extends JpaRepository<FamilyDebt, Long> {
    List<FamilyDebt> findAllByFamilyId(Long familyId);
}
