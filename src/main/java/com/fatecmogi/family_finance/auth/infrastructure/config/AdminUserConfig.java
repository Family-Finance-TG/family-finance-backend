package com.fatecmogi.family_finance.auth.infrastructure.config;

import com.fatecmogi.family_finance.auth.infrastructure.entity.Permission;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import com.fatecmogi.family_finance.user.infrastructure.entity.GenderEnum;
import com.fatecmogi.family_finance.auth.infrastructure.repository.PermissionRepository;
import com.fatecmogi.family_finance.user.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class AdminUserConfig implements CommandLineRunner {
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var userAdmin = userRepository.findByAccessName("teste");

        if(userAdmin.isPresent()){
            System.out.println("Admin user already exists");
            return;
        }

        Set<Permission> allPermissions = new HashSet<>(permissionRepository.findAll());

        User user = new User();
        user.setAccessName("teste");
        user.setPassword(passwordEncoder.encode("teste"));
        user.setInviteCode(UUID.randomUUID());
        user.setPermissions(allPermissions);
        user.setName("Usuário Teste");
        user.setDateBirth(LocalDateTime.of(2003, 5, 18, 2, 23, 40));
        user.setCpf("12345678900");
        user.setGender(GenderEnum.MALE);
        user.setActive(true);

        userRepository.save(user);
    }
}
