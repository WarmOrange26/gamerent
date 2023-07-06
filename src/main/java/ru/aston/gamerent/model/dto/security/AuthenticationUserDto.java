package ru.aston.gamerent.model.dto.security;

import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import java.util.Set;

public record AuthenticationUserDto(
        String email,
        Set<RoleNameEnum> roleNames) {
}
