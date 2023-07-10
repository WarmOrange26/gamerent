package ru.aston.gamerent.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.generator.DtoGenerator;
import ru.aston.gamerent.generator.EntityGenerator;
import ru.aston.gamerent.mapper.UserMapper;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.repository.AccountRepository;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.repository.WalletRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Long WRONG_USER_ID = 0L;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserResponseDto userResponseDto;
    private UserRequestDto userRequestDto;
    private UserDto userDto;
    private RegistrationUserRequestDto registrationUserRequestDto;
    private List<Account> accounts;
    private List<Wallet> wallets;

    @BeforeEach
    void setup() {
        EntityGenerator entityGenerator = new EntityGenerator();
        DtoGenerator dtoGenerator = new DtoGenerator();
        user = entityGenerator.getUser();
        userResponseDto = dtoGenerator.getUserResponseDto();
        userRequestDto = dtoGenerator.getUserRequestDto();
        userDto = dtoGenerator.getUserDto();
        registrationUserRequestDto = dtoGenerator.getRegistrationUserRequestDto();
        accounts = List.of(entityGenerator.getAccount());
        wallets = List.of(entityGenerator.getWallet());
    }

    @Test
    void getUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        when(accountRepository.findAccountsByUser(user)).thenReturn(accounts);
        when(walletRepository.findWalletsByUser(user)).thenReturn(wallets);
        when(userMapper.userToUserResponseDto(user, wallets, accounts)).thenReturn(userResponseDto);

        assertThat(userService.getUserById(user.getId())).isEqualTo(userResponseDto);
    }

    @Test
    void getUserByIdThrowException() {
        when(userRepository.findById(WRONG_USER_ID))
                .thenThrow(new NoEntityException("User with id " + WRONG_USER_ID + " was not found"));

        assertThatThrownBy(() -> userService.getUserById(WRONG_USER_ID))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("User with id " + WRONG_USER_ID + " was not found");
    }

    @Test
    void updateUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        when(userMapper.userRequestToUser(userRequestDto, user)).thenReturn(user);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(userDto);
        when(userRepository.findById(WRONG_USER_ID))
                .thenThrow(new NoEntityException("User with id " + WRONG_USER_ID + " was not found"));

        assertThat(userService.updateUser(user.getId(), userRequestDto)).isEqualTo(userDto);
        assertThatThrownBy(() -> userService.getUserById(WRONG_USER_ID))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("User with id " + WRONG_USER_ID + " was not found");
    }

    @Test
    void updateUserThrowException() {
        when(userRepository.findById(WRONG_USER_ID))
                .thenThrow(new NoEntityException("User with id " + WRONG_USER_ID + " was not found"));

        assertThatThrownBy(() -> userService.getUserById(WRONG_USER_ID))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("User with id " + WRONG_USER_ID + " was not found");
    }

    @Test
    void saveUser() {
        when(userRepository.findByEmail(registrationUserRequestDto.email())).thenReturn(Optional.empty());
        when(userMapper.userRegistrationDtoToUser(registrationUserRequestDto)).thenReturn(user);
        when(passwordEncoder.encode(registrationUserRequestDto.password())).thenReturn("encodePassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(userDto);

        assertThat(userService.saveUser(registrationUserRequestDto)).isEqualTo(userDto);
    }

    @Test
    void saveUserExisting() {
        when(userRepository.findByEmail(registrationUserRequestDto.email())).thenReturn(Optional.ofNullable(user));

        assertThat(userService.saveUser(registrationUserRequestDto)).isEqualTo(UserDto.builder().build());
    }
}