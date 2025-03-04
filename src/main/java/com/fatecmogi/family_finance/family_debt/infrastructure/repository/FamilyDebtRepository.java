package com.fatecmogi.family_finance.family_debt.infrastructure.repository;

import com.fatecmogi.family_finance.family_debt.infrastructure.entity.FamilyDebt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyDebtRepository extends JpaRepository<FamilyDebt, Long> {

}
