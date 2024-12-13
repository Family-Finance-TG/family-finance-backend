package com.fatecmogi.family_finance.infrastructure.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_friendly_name", nullable = false, length = 50)
    private String frindlyName;

    @Column(name = "role_value", nullable = false, length = 50)
    private String value;

    //TODO: Implementar a criação dos itens automaticamente no banco usando o enum.
    @Getter
    @AllArgsConstructor
    public enum Values {
        BASIC(1, "Basic", "BASIC"),
        ADMIN(2, "Administrator", "ADMIN");

        long id;
        String friendlyName;
        String value;
    }
}
