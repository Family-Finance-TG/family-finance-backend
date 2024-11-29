package com.fatecmogi.family_finance.domain.mapper.family;

import com.fatecmogi.family_finance.application.dto.family.FamilyDTO;
import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.domain.mapper.IMapper;
import com.fatecmogi.family_finance.domain.mapper.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.Family;
import com.fatecmogi.family_finance.infrastructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class FamilyMapper implements IMapper<Family, FamilyDTO> {

    private final GenderMapper genderMapper;

    public FamilyMapper(GenderMapper genderMapper) {
        this.genderMapper = genderMapper;
    }


    @Override
    public FamilyDTO toDTO(Family entity) {
        return new FamilyDTO(
                entity.getId(),
                entity.getName(),
                entity.getMembers().stream()
                        .map(user -> new UserDTO(
                                null,
                                user.getName(),
                                null,
                                null,
                                user.getDateBirth(),
                                user.getSalary(),
                                user.getPercentageSalary(),
                                null,
                                null
                        ))
                        .collect(Collectors.toSet()
        )
        );
    }

    @Override
    public Family toEntity(FamilyDTO dto) {
        Family entity = new Family();
        updateEntity(dto, entity);
        return entity;
    }

    public Family toEntity(FamilyDTO dto, User creator) {
        Family entity = new Family();
        entity.setMembers(new HashSet<User>());
        entity.getMembers().add(creator);

        updateEntity(dto, entity);
        return entity;
    }

    @Override
    public void updateEntity(FamilyDTO dto, Family entity) {
        entity.setName(dto.name());

        if (dto.members() == null) {
            return;
        }
        entity.setMembers(dto.members().stream()
                .map(userDTO -> {
                    User user = new User();
                    user.setId(userDTO.id());
                    user.setName(userDTO.name());
                    user.setAccessName(userDTO.accessName());
                    user.setPassword(userDTO.password());
                    user.setDateBirth(userDTO.dateBirth());
                    user.setSalary(userDTO.salary());
                    user.setPercentageSalary(userDTO.percentageSalary());
                    user.setCpf(userDTO.cpf());
                    return user;
                })
                .collect(Collectors.toSet())
        );
    }

}
