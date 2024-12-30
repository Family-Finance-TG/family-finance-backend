package com.fatecmogi.family_finance.infrastructure.entity.family;

import com.fatecmogi.family_finance.infrastructure.entity.BaseEntity;
import com.fatecmogi.family_finance.infrastructure.entity.family.debt.FamilyDebt;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_family_id")
    private Set<User> members;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_debt_family_id")
    private Set<FamilyDebt> debts;
}
