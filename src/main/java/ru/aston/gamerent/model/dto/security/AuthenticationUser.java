package ru.aston.gamerent.model.dto.security;

import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import java.util.Set;

public record AuthenticationUser(
        String email,
        Set<RoleNameEnum> roleNames) {
}
