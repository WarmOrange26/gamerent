package ru.aston.gamerent.model.dto.security;

import lombok.Builder;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import java.util.Set;

@Builder
public record AuthenticationUserDto(
        String email,
        Set<RoleNameEnum> roleNames) {
}
