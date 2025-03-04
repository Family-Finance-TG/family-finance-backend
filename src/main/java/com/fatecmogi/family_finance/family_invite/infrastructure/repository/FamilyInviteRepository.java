package com.fatecmogi.family_finance.family_invite.infrastructure.repository;

import com.fatecmogi.family_finance.family_invite.infrastructure.entity.FamilyInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyInviteRepository extends JpaRepository<FamilyInvite, Long> {
    List<FamilyInvite> findAllByFamilyId(Long familyId);
    List<FamilyInvite> findAllByReceiverId(Long receiverId);
}
