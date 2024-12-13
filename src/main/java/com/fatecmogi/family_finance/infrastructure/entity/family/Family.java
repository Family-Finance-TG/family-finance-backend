package com.fatecmogi.family_finance.infrastructure.entity.family;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fatecmogi.family_finance.infrastructure.entity.BaseEntity;
import com.fatecmogi.family_finance.infrastructure.entity.family.debt.FamilyDebt;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<User> members;

    @OneToMany(mappedBy = "family", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<FamilyDebt> debts;
}
