package com.fatecmogi.family_finance.user.domain.mapper;

import com.fatecmogi.family_finance.auth.infrastructure.entity.Permission;
import com.fatecmogi.family_finance.auth.infrastructure.entity.PermissionEnum;
import com.fatecmogi.family_finance.user.application.dto.request.CreateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.request.UpdateUserDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserDetailsResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryResponseDTO;
import com.fatecmogi.family_finance.user.application.dto.response.UserSummaryWithPermissionsDTO;
import com.fatecmogi.family_finance.user.infrastructure.entity.User;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = GenderMapper.class)
public interface UserMapper {

    UserSummaryResponseDTO toSummaryDTO(User entity);

    @Mapping(source = "family.id", target = "familyId")
    @Mapping(target = "permissions", ignore = true)
    UserDetailsResponseDTO toDetailsDTOWithoutPermissions(User entity);

    User toEntity(CreateUserDTO dto);

    void updateEntity(UpdateUserDTO dto, @MappingTarget User entity);

    default UserDetailsResponseDTO toDetailsDTO(User user) {
        UserDetailsResponseDTO dto = toDetailsDTOWithoutPermissions(user);
        List<String> permissions = CollectionUtils.isEmpty(user.getPermissions())
                ? Collections.emptyList()
                : user.getPermissions().stream()
                .map(Permission::getValue)
                .collect(Collectors.toList());

        return new UserDetailsResponseDTO(
                dto.id(),
                dto.name(),
                dto.dateBirth(),
                dto.inviteCode(),
                dto.salary(),
                dto.percentageSalary(),
                dto.cpf(),
                dto.gender(),
                dto.familyId(),
                permissions
        );
    }

    default Map<String, Boolean> mapPermissions(User user) {
        Set<String> userPermissions = Optional.ofNullable(user.getPermissions())
                .orElse(Collections.emptySet())
                .stream()
                .map(Permission::getValue)
                .collect(Collectors.toSet());

        Map<String, Boolean> permissionMap = new HashMap<>();

        for (PermissionEnum permission : PermissionEnum.values()) {
            permissionMap.put(permission.getValue(), userPermissions.contains(permission.getValue()));
        }

        return permissionMap;
    }

    default UserSummaryWithPermissionsDTO toSummaryWithPermissionsDTO(User user) {
        return new UserSummaryWithPermissionsDTO(
                user.getId(),
                user.getName(),
                user.isActive(),
                mapPermissions(user)
        );
    }
}
