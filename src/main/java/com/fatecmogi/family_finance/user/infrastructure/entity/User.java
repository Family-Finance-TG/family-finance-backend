package com.fatecmogi.family_finance.user.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fatecmogi.family_finance.auth.infrastructure.entity.PermissionEnum;
import com.fatecmogi.family_finance.common.infrastructure.entity.BaseEntity;
import com.fatecmogi.family_finance.auth.infrastructure.entity.Permission;
import com.fatecmogi.family_finance.family.infrastructure.entity.Family;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "user_created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "user_updated_at"))
public class User extends BaseEntity {

    @Column(name = "user_access_name", nullable = false, length = 100)
    private String accessName;

    @Column(name = "user_password", nullable = false, length = 100)
    private String password;

    @Column(name="user_invite_code", nullable = false, length = 36)
    private UUID inviteCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(name = "user_date_birth", nullable = false)
    private LocalDateTime dateBirth;

    @Column(name = "user_cpf", nullable = false, length = 11)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender", nullable = false, length = 50)
    private GenderEnum gender;

    @Column(name = "user_active", nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_family_id")
    @JsonBackReference
    private Family family;

    public boolean hasPermission(PermissionEnum permissionEnum) {
        if (this.permissions == null || permissionEnum == null) {
            return false;
        }

        return this.permissions.stream()
                .anyMatch(permission -> permission.getValue().equals(permissionEnum.getValue()));
    }
}
