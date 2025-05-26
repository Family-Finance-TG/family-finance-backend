package com.fatecmogi.family_finance.auth.domain.util;

import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.auth.infrastructure.entity.Role;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class PermissionValidator {

    private final UserRepository userRepository;

    public PermissionValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(JwtAuthenticationToken token, String requiredPermission) {
        Long userId = Long.parseLong(token.getName());
        User user = userRepository.findById(userId).orElseThrow();

        boolean hasPermission = user.getRoles().stream()
                .map(Role::getValue)
                .anyMatch(roleValue -> roleValue.equals(requiredPermission));

        if (!hasPermission) {
            throw new RuntimeException("Acesso negado: permissão '" + requiredPermission + "' é exigida.");
        }
    }
}
