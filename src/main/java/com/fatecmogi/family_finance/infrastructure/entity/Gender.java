package com.fatecmogi.family_finance.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "gender_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "gender_created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "gender_updated_at"))
public class Gender extends BaseEntity {

    @Column(name = "gender_friendly_name", nullable = false, length = 50)
    private String friendlyName;

    @Column(name = "gender_value", nullable = false, length = 50)
    private String value;
}