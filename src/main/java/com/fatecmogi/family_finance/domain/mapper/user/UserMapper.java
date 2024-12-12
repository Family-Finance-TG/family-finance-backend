package com.fatecmogi.family_finance.domain.mapper.user;

import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.domain.mapper.BaseMapper;
import com.fatecmogi.family_finance.infrastructure.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR)
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
