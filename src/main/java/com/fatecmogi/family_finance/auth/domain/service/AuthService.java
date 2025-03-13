package com.fatecmogi.family_finance.auth.domain.service;

import com.fatecmogi.family_finance.auth.application.dto.LoginRequestDTO;
import com.fatecmogi.family_finance.auth.application.dto.LoginResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.request.CreateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserBaseResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.common.domain.exception.FFAuthenticationException;
import com.fatecmogi.family_finance.user.domain.mapper.UserMapper;
import com.fatecmogi.family_finance.auth.infrastructure.entity.Role;
import com.fatecmogi.family_finance.auth.infrastructure.entity.RoleEnum;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.user.infrastructure.entity.GenderEnum;
import com.fatecmogi.family_finance.auth.infrastructure.repository.RoleRepository;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var user = userRepository.findByAccessName(loginRequestDTO.accessName());

        if (user.isEmpty() || !passwordMatches(loginRequestDTO, user.get())) {
            throw new FFAuthenticationException();
        }

        Instant now = Instant.now();
        long expiresIn = 300L;

        var scopes = user.get().getRoles()
                .stream()
                .map(Role::getValue)
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
        Role basicRole = roleRepository.findByValue(RoleEnum.BASIC.getValue());

        entity.setInviteCode(UUID.randomUUID());
        entity.setPassword(passwordEncoder.encode(dto.password()));
        entity.setRoles(Set.of(basicRole));
        entity.setGender(GenderEnum.fromValue(dto.gender().value()));
        entity.setActive(true);
    }

    public void save(CreateUserDTO dto) {
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
}
