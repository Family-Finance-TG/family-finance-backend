package com.fatecmogi.family_finance.user.application.dto.response;

import com.fatecmogi.family_finance.common.application.dto.IDTO;

import java.util.Map;

public record UserSummaryWithPermissionsDTO(
        Long id,
        String name,
        Boolean active,
        Map<String, Boolean> permissions

) implements IDTO {}
