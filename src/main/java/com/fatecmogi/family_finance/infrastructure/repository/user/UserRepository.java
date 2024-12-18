package com.fatecmogi.family_finance.infrastructure.repository.user;

import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccessName(String accessName);
}
