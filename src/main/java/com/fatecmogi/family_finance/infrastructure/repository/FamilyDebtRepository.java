package com.fatecmogi.family_finance.infrastructure.repository;

import com.fatecmogi.family_finance.infrastructure.entity.family_debt.FamilyDebt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyDebtRepository extends JpaRepository<FamilyDebt, Long> {

}
