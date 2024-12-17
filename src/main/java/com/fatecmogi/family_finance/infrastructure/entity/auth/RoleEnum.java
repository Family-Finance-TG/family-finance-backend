package com.fatecmogi.family_finance.infrastructure.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    BASIC(1, "BASIC", "Basic"),
    ADMIN(2,  "ADMIN", "Administrator");

    long id;
    String value;
    String friendlyName;
}
