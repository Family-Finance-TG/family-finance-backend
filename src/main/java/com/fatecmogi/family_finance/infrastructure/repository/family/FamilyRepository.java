package com.fatecmogi.family_finance.infrastructure.repository.family;

import com.fatecmogi.family_finance.infrastructure.entity.family.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {

}
