package com.fatecmogi.family_finance.auth.domain.util;

import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthUserRecover {
    private final UserRepository userRepository;


    public User getByToken(JwtAuthenticationToken token) {
        long userId = Long.parseLong(token.getName());

        return userRepository.findById(userId).orElse(null);
    }
}
