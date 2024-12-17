package com.fatecmogi.family_finance.infrastructure.config;

import com.fatecmogi.family_finance.infrastructure.entity.auth.RoleEnum;
import com.fatecmogi.family_finance.infrastructure.entity.user.gender.GenderEnum;
import com.fatecmogi.family_finance.infrastructure.entity.auth.Role;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import com.fatecmogi.family_finance.infrastructure.repository.auth.RoleRepository;
import com.fatecmogi.family_finance.infrastructure.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role roleAdmin = roleRepository.findByValue(RoleEnum.ADMIN.getValue());

        var userAdmin = userRepository.findByAccessName("admin");

        if(userAdmin.isPresent()){
            System.out.println("Admin user already exists");
            return;
        }

        User user = new User();
        user.setAccessName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRoles(Set.of(roleAdmin));
        user.setName("Usuário Administrador");
        user.setDateBirth(LocalDateTime.of(2003, 5, 18, 2, 23, 40));
        user.setSalary(1420.4f);
        user.setPercentageSalary(10.0f);
        user.setCpf("12345678900");
        user.setGender(GenderEnum.MALE);
        user.setActive(true);

        userRepository.save(user);
    }
}
