package com.fatecmogi.family_finance.auth.domain.service;

import com.fatecmogi.family_finance.auth.application.dto.LoginRequestDTO;
import com.fatecmogi.family_finance.auth.application.dto.LoginResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.request.CreateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserBaseResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.common.domain.exception.FFUnauthorizedException;
import com.fatecmogi.family_finance.user.domain.mapper.UserMapper;
import com.fatecmogi.family_finance.auth.infrastructure.entity.Permission;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.user.infrastructure.entity.GenderEnum;
import com.fatecmogi.family_finance.auth.infrastructure.repository.PermissionRepository;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var user = userRepository.findByAccessName(loginRequestDTO.accessName());

        if (user.isEmpty() || !passwordMatches(loginRequestDTO, user.get())) {
            throw new FFUnauthorizedException();
        }

        Instant now = Instant.now();
        long expiresIn = 3600L;

        var scopes = user.get().getPermissions()
                .stream()
                .map(Permission::getValue)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("family-finance")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresIn, user.get().getId());
    }
    private void savePre(CreateUserDTO dto, User entity) {
        entity.setInviteCode(UUID.randomUUID());
        entity.setPassword(passwordEncoder.encode(dto.password()));
        entity.setGender(GenderEnum.fromValue(dto.gender().value()));
        entity.setActive(true);
    }
    public void save(CreateUserDTO dto) {
        validatePasswordStrength(dto.password());

        User entity = userMapper.toEntity(dto);
        savePre(dto, entity);
        UserDetailsResponseDTO savedDTO = userMapper.toDetailsDTO(userRepository.save(entity));
        savePos(savedDTO, entity);
    }
    private void savePos(UserBaseResponseDTO dto, User entity) {
    }
    private boolean passwordMatches(LoginRequestDTO loginRequestDTO, User user) {
        return passwordEncoder.matches(loginRequestDTO.password(), user.getPassword());
    }
    private void validatePasswordStrength(String password) {
        if (password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*\\d.*") ||
                !password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais.");
        }
    }
}
