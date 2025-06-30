package com.fatecmogi.family_finance.auth.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "permission_value", nullable = false, length = 50)
    private String value;

    @Column(name = "permission_description", nullable = false, length = 50)
    private String description;

}
