package com.fatecmogi.family_finance.domain.mapper.user;

import com.fatecmogi.family_finance.application.dto.user.UserDTO;
import com.fatecmogi.family_finance.domain.mapper.BaseMapper;
import com.fatecmogi.family_finance.domain.mapper.user.gender.GenderMapper;
import com.fatecmogi.family_finance.infrastructure.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = org.mapstruct.InjectionStrategy.CONSTRUCTOR, uses = GenderMapper.class)
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
