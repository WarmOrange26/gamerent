package ru.aston.gamerent.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aston.gamerent.model.dto.security.AuthenticationUserDto;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.SettingValue;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AuthenticationUserMapperImpl.class})
class AuthenticationUserMapperTest {

    @Autowired
    private AuthenticationUserMapper authenticationUserMapper;

    private static final User USER = User.builder()
            .id(1L)
            .username("JD")
            .email("example@example.com")
            .password("Gamerent123456@")
            .phone("375291112233")
            .firstName("Jake")
            .lastName("Dean")
            .birthDate(LocalDate.now())
            .isBlocked(false)
            .registrationTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .roles(Set.of(new Role(1L, RoleNameEnum.ROLE_USER)))
            .settings(List.of(new SettingValue()))
            .build();

    @Test
    void shouldMapUserToAuthenticationUserDto() {
        AuthenticationUserDto userDto = authenticationUserMapper.userToAuthenticationUserDto(USER);
        assertNotNull(userDto);
        assertEquals(USER.getEmail(), userDto.email());
        assertEquals(USER.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet()), userDto.roleNames());
    }
}