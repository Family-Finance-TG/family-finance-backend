package com.fatecmogi.family_finance.domain.util;

import com.fatecmogi.family_finance.infrastructure.entity.User;
import com.fatecmogi.family_finance.infrastructure.repository.UserRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthUserRecover {

    private final UserRepository userRepository;

    public AuthUserRecover(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByToken(JwtAuthenticationToken token) {
        long userId = Long.parseLong(token.getName());

        return userRepository.findById(userId).orElse(null);
    }
}
