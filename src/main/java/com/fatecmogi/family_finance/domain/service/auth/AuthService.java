package com.fatecmogi.family_finance.domain.service.auth;

import com.fatecmogi.family_finance.application.dto.IDTO;
import com.fatecmogi.family_finance.application.dto.auth.LoginRequestDTO;
import com.fatecmogi.family_finance.application.dto.auth.LoginResponseDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.domain.exception.FFAuthenticationException;
import com.fatecmogi.family_finance.domain.mapper.user.UserMapper;
import com.fatecmogi.family_finance.infrastructure.entity.Gender;
import com.fatecmogi.family_finance.infrastructure.entity.Role;
import com.fatecmogi.family_finance.infrastructure.entity.User;
import com.fatecmogi.family_finance.infrastructure.repository.GenderRepository;
import com.fatecmogi.family_finance.infrastructure.repository.RoleRepository;
import com.fatecmogi.family_finance.infrastructure.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public AuthService(UserRepository userRepository, GenderRepository genderRepository, RoleRepository roleRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
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

        return new LoginResponseDTO(jwtValue, expiresIn);

    }

    private void savePre(UserDTO dto, User entity) {
        Gender gender = genderRepository.findById(dto.gender().id()).orElseThrow();
        Role basicRole = roleRepository.findByValue(Role.Values.BASIC.getValue());

        entity.setPassword(passwordEncoder.encode(dto.password()));
        entity.setRoles(Set.of(basicRole));
        entity.setGender(gender);
        entity.setActive(true);
    }

    public UserDTO save(UserDTO dto) {
        User entity = userMapper.toEntity(dto);

        savePre(dto, entity);
        UserDTO savedDTO = userMapper.toDTO(userRepository.save(entity));
        savePos(savedDTO, entity);

        return savedDTO;
    }

    private void savePos(IDTO dto, User entity) {

    }

    private boolean passwordMatches(LoginRequestDTO loginRequestDTO, User user) {
        return passwordEncoder.matches(loginRequestDTO.password(), user.getPassword());
    }
}
