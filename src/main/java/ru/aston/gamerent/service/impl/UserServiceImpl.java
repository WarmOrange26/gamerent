package ru.aston.gamerent.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.mapper.ConfirmationTokenMapper;
import ru.aston.gamerent.mapper.UserMapper;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.ConfirmationToken;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.repository.AccountRepository;
import ru.aston.gamerent.repository.ConfirmationTokenRepository;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.repository.WalletRepository;
import ru.aston.gamerent.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.lang.Boolean.FALSE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final long USER_ROLE_ID = 1L;
    private final ConfirmationTokenMapper confirmationTokenMapper = Mappers.getMapper(ConfirmationTokenMapper.class);
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoEntityException("User with id " + email + " was not found"));
        List<Account> accounts = accountRepository.findAccountsByUser(user);
        List<Wallet> wallets = walletRepository.findWalletsByUser(user);

        return userMapper.userToUserResponseDto(user, wallets, accounts);
    }

    @Override
    public UserDto updateUser(String email, UserRequestDto userRequestDto) {
        return userRepository.findByEmail(email)
                .map(user -> userMapper.userRequestToUser(userRequestDto, user))
                .map(userRepository::saveAndFlush)
                .map(userMapper::userToUserDto)
                .orElseThrow(() -> new NoEntityException("User with email " + email + " was not found"));
    }

    @Override
    @Transactional
    public Optional<ConfirmationResponseDto> saveUser(RegistrationUserRequestDto registrationUserRequestDto) {
        Optional<User> userFromDB = userRepository.findByEmail(registrationUserRequestDto.email());

        if (userFromDB.isPresent()) {
            log.info("User with email {} already exists!", registrationUserRequestDto.email());
            return Optional.empty();
        }

        User newUser = userMapper.userRegistrationDtoToUser(registrationUserRequestDto);
        fillUserWithExtraData(newUser);
        userRepository.save(newUser);

        ConfirmationToken confirmationToken = new ConfirmationToken(newUser);
        confirmationTokenRepository.save(confirmationToken);

        log.info("User {} successfully saved. Confirmation token: {}", newUser, confirmationToken.getToken());

        return Optional.of(confirmationTokenMapper.confirmationTokenToDto(confirmationToken));
    }

    @Override
    public boolean confirmEmail(UUID token) {
        return confirmationTokenRepository.findByToken(token)
                .map(confirmationToken -> confirmationToken.getUser().getEmail())
                .flatMap(userRepository::findByEmail)
                .map(user -> unblockedConfirmedUser(user, token))
                .isPresent();
    }

    private void fillUserWithExtraData(User newUser) {
        newUser.setRoles(Set.of(new Role(USER_ROLE_ID, RoleNameEnum.ROLE_USER)));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRegistrationTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());
        newUser.setIsBlocked(true);
    }

    private User unblockedConfirmedUser(User user, UUID token) {
        user.setIsBlocked(FALSE);
        User updatedUser = userRepository.save(user);
        log.info("User {} successfully confirm email by token {}", user.getEmail(), token);

        return updatedUser;
    }
}
