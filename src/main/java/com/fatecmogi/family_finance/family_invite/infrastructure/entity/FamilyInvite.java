package com.fatecmogi.family_finance.family_invite.infrastructure.entity;

import com.fatecmogi.family_finance.common.infrastructure.entity.BaseEntity;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "family_invites")
@AttributeOverride(name = "id", column = @Column(name = "family_invite_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "family_invite_created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "family_invite_updated_at"))
public class FamilyInvite extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "family_invite_family_id")
    private Family family;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_invite_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_invite_receiver_id")
    private User receiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "family_invite_status")
    private FamilyInviteStatusEnum status;
}
