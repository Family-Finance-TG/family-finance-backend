package com.fatecmogi.family_finance.auth.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionEnum {
    PERMISSION_MANAGE("PERMISSION_MANAGE", "Gerenciar permissões"),
    DEBT_ADD("DEBT_ADD", "Adicionar débito"),
    DEBT_EDIT("DEBT_EDIT", "Editar débito"),
    DEBT_DELETE("DEBT_DELETE", "Editar débito"),
    MEMBER_INVITE("MEMBER_INVITE", "Convidar membros"),
    MEMBER_REMOVE("MEMBER_REMOVE", "Remover membros");
    
    private final String value;
    private final String description;

}
