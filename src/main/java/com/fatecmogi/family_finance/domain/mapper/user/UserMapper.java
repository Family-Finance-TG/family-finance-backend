package com.fatecmogi.family_finance.domain.mapper.user;

import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.application.dto.user.request.CreateUserDTO;
import com.fatecmogi.family_finance.application.dto.user.request.UpdateUserDTO;
import com.fatecmogi.family_finance.application.dto.user.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.application.dto.user.response.UserSummaryResponseDTO;
import com.fatecmogi.family_finance.domain.mapper.BaseMapper;
import com.fatecmogi.family_finance.domain.mapper.user.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR, uses = GenderMapper.class)
public interface UserMapper {
    UserSummaryResponseDTO toSummaryDTO(User entity);
    UserDetailsResponseDTO toDetailsDTO(User entity);

    User toEntity(CreateUserDTO dto);

    void updateEntity(UpdateUserDTO dto, @MappingTarget User entity);
}
