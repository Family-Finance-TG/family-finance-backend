package com.fatecmogi.family_finance.domain.mapper.user;

import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.domain.mapper.IMapper;
import com.fatecmogi.family_finance.domain.mapper.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<User, UserDTO> {
    private final GenderMapper genderMapper;

    public UserMapper(GenderMapper genderMapper) {
        this.genderMapper = genderMapper;
    }

    @Override
    public UserDTO toDTO(User entity) {
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getAccessName(),
                entity.getPassword(),
                entity.getDateBirth(),
                entity.getSalary(),
                entity.getPercentageSalary(),
                entity.getCpf(),
                genderMapper.toDTO(entity.getGender())
        );
    }

    @Override
    public User toEntity(UserDTO dto) {
        User entity = new User();
        updateEntity(dto, entity);
        return entity;
    }

    @Override
    public void updateEntity(UserDTO dto, User entity) {
        entity.setName(dto.name());
        entity.setAccessName(dto.accessName());
        entity.setPassword(dto.password());
        entity.setDateBirth(dto.dateBirth());
        entity.setSalary(dto.salary());
        entity.setPercentageSalary(dto.percentageSalary());
        entity.setCpf(dto.cpf());
        entity.setGender(genderMapper.toEntity(dto.gender()));
    }
}
