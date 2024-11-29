package com.fatecmogi.family_finance.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private long id;

    @Column(name = "gender_friendly_name", nullable = false, length = 50)
    private String friendlyName;

    @Column(name = "gender_value", nullable = false, length = 50)
    private String value;

    @AllArgsConstructor
    @Getter
    public enum Values {
        NOT_DEFINED(1, "Not defined", "NOT_DEFINED"),
        FEMALE(2, "Female", "FEMALE"),
        MALE(3, "Male", "MALE");

        private long id;
        private String friendlyName;
        private String value;

        public Gender toGender() {
            return new Gender(id, friendlyName, value);
        }
    }
}