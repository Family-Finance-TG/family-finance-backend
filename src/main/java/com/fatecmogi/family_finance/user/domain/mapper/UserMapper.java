package com.fatecmogi.family_finance.user.domain.mapper;

import com.fatecmogi.family_finance.user.application.dto.request.CreateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.request.UpdateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR, uses = GenderMapper.class)
public interface UserMapper {
    UserSummaryResponseDTO toSummaryDTO(User entity);
    UserDetailsResponseDTO toDetailsDTO(User entity);

    User toEntity(CreateUserDTO dto);

    void updateEntity(UpdateUserDTO dto, @MappingTarget User entity);
}
