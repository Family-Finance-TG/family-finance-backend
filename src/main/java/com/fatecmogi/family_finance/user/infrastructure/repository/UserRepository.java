package com.fatecmogi.family_finance.user.infrastructure.repository;

import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccessName(String accessName);

    Optional<User> findByInviteCode(UUID inviteCode);

    List<User> findAllByActiveTrue();

    Optional<User> findByIdAndActiveTrue(Long id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.family WHERE u.id = :id")
    Optional<User> findByIdWithFamily(@Param("id") Long id);

    @Query("SELECT u.family.id FROM User u WHERE u.id = :id")
    Optional<Long> getFamilyIdByUserId(@Param("id") Long id);

    @Query("""
    SELECT u FROM User u
    LEFT JOIN FETCH u.family f
    LEFT JOIN FETCH u.roles
    LEFT JOIN FETCH f.members m
    LEFT JOIN FETCH m.roles
    WHERE u.id = :userId
""")
    Optional<User> findByIdWithFamilyAndRoles(@Param("userId") Long userId);


}
