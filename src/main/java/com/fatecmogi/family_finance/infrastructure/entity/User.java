package com.fatecmogi.family_finance.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(name = "user_access_name", nullable = false, length = 100)
    private String accessName;

    @Column(name = "user_password", nullable = false, length = 100)
    private String password;

    @Column(name = "user_date_birth", nullable = false)
    private LocalDateTime dateBirth;

    @Column(name = "user_salary", nullable = false)
    private float salary;

    @Column(name = "user_percentage_salary", nullable = false)
    private float percentageSalary;

    @Column(name = "user_cpf", nullable = false, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "user_gender_id", nullable = false)
    private Gender gender;
}
