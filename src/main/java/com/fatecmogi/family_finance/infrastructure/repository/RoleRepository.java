package com.fatecmogi.family_finance.infrastructure.repository;

import com.fatecmogi.family_finance.infrastructure.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByValue(String value);
}
