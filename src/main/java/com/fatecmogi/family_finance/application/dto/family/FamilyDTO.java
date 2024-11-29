package com.fatecmogi.family_finance.application.dto.family;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FamilyDTO(
        long id,
        String name,
        Set<UserDTO> members
) implements IDTO {
}
