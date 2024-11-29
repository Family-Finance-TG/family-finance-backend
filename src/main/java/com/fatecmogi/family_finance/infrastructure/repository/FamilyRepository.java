package com.fatecmogi.family_finance.infrastructure.repository;

import com.fatecmogi.family_finance.infrastructure.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {

}
