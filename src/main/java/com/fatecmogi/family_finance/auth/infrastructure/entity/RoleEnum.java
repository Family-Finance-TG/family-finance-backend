package com.fatecmogi.family_finance.auth.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    BASIC(1, "BASIC", "Basic"),
    ADMIN(2,  "ADMIN", "Administrator"),
    CAN_ADD(3, "CAN_ADD", "Adicionar Lançamentos"),
    CAN_EDIT(4, "CAN_EDIT", "Editar Lançamentos"),
    CAN_DELETE(5, "CAN_DELETE", "Excluir Lançamentos"),
    CAN_INVITE(6, "CAN_INVITE", "Convidar Membros"),
    CAN_REMOVE(7, "CAN_REMOVE", "Expulsar Membros");

    long id;
    String value;
    String friendlyName;
}
