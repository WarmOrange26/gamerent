package ru.aston.gamerent.dto;


import ru.aston.gamerent.model.enumeration.RoleNameEnum;

import java.util.Set;

public record AuthenticationUserDto(String email, Set<RoleNameEnum> roleNames) {
}
