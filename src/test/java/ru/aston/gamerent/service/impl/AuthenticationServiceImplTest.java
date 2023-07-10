package ru.aston.gamerent.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aston.gamerent.exception.BadRequestException;
import ru.aston.gamerent.mapper.AuthenticationUserMapper;
import ru.aston.gamerent.model.dto.security.AuthenticationRequestDto;
import ru.aston.gamerent.model.dto.security.AuthenticationUserDto;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.SettingValue;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationUserMapper authenticationUserMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private static final AuthenticationRequestDto LOGIN_DATA = AuthenticationRequestDto.builder()
            .email("example@example.com")
            .password("Gamerent123456@")
            .build();

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

    public static final AuthenticationUserDto USER_DTO = AuthenticationUserDto.builder()
            .email("example@example.com")
            .roleNames(Set.of(RoleNameEnum.ROLE_USER))
            .build();


    @Test
    void shouldFindUserByValidRequestData() {
        when(userRepository.findByEmail(LOGIN_DATA.email())).thenReturn(Optional.of(USER));
        when(passwordEncoder.matches(LOGIN_DATA.password(), USER.getPassword())).thenReturn(true);
        when(authenticationUserMapper.userToAuthenticationUserDto(USER)).thenReturn(USER_DTO);
        AuthenticationUserDto userDto = authenticationService.findByEmailAndPassword(LOGIN_DATA);
        assertEquals(userDto.email(), USER.getEmail());
        assertEquals(userDto.roleNames(), USER.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
    }

    @Test
    void shouldNotFindUserByInvalidRequestDataAndThrowException() {
        when(userRepository.findByEmail(LOGIN_DATA.email())).thenReturn(Optional.of(USER));
        when(passwordEncoder.matches(LOGIN_DATA.password(), USER.getPassword())).thenReturn(false);
        assertThrows(BadRequestException.class, () -> authenticationService.findByEmailAndPassword(LOGIN_DATA));
    }
}