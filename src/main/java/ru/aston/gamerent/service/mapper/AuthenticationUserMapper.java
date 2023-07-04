package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.aston.gamerent.model.dto.security.AuthenticationUser;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthenticationUserMapper {

    @Mapping(target = "roleNames", expression = "java(rolesToRoleNames(user.getRoles()))")
    AuthenticationUser userToAuthenticationUserDto(User user);

    default Set<RoleNameEnum> rolesToRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
