package com.fatecmogi.family_finance.user.infrastructure.repository;

import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccessName(String accessName);
}
