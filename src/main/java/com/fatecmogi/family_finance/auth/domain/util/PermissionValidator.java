package com.fatecmogi.family_finance.auth.domain.util;

import com.fatecmogi.family_finance.auth.infrastructure.entity.PermissionEnum;
import com.fatecmogi.family_finance.common.domain.exception.FFForbiddenException;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.auth.infrastructure.entity.Permission;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PermissionValidator {
    private final UserRepository userRepository;

    public void hasPermissionOrThrow(JwtAuthenticationToken token, PermissionEnum requiredPermission) {
        Long userId = Long.parseLong(token.getName());
        User user = userRepository.findById(userId).orElseThrow();
        validate(user, requiredPermission);
    }

    public void hasPermissionOrThrow(User user, PermissionEnum requiredPermission) {
        validate(user, requiredPermission);
    }

    private void validate(User user, PermissionEnum requiredPermission) {
        boolean hasPermission = user.getPermissions().stream()
                .map(Permission::getValue)
                .anyMatch(roleValue -> roleValue.equals(requiredPermission.getValue()));

        if (!hasPermission) {
            throw new FFForbiddenException();
        }
    }
}
