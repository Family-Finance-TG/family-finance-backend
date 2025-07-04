package com.fatecmogi.family_finance.family.infrastructure.repository;

import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    @Query("SELECT f FROM Family f LEFT JOIN FETCH f.members WHERE f.id = :id")
    Optional<Family> findByIdWithMembers(@Param("id") Long id);
}
