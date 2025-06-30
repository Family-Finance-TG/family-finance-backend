package com.fatecmogi.family_finance.family.infrastructure.entity;

import com.fatecmogi.family_finance.common.infrastructure.entity.BaseEntity;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "families")
@AttributeOverride(name = "id", column = @Column(name = "family_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "family_created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "family_updated_at"))
public class Family extends BaseEntity {

    @Column(name = "family_name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "family", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> members;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
}
