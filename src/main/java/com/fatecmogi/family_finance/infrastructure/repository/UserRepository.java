package com.fatecmogi.family_finance.infrastructure.repository;

import com.fatecmogi.family_finance.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
