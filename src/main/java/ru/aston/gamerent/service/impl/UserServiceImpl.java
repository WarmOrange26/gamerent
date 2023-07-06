package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.mapper.ConfirmationTokenMapper;
import ru.aston.gamerent.mapper.UserMapper;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ConfirmationTokenMapper confirmationTokenMapper;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));
        List<Account> accounts = accountRepository.findAccountsByUser(user);
        List<Wallet> wallets = walletRepository.findWalletsByUser(user);

        return userMapper.userToUserResponseDto(user, wallets, accounts);
    }

    @Override
    public void updateUser(Long id, UserRequestDto userRequestDto) {
        User userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));

        userRepository.saveAndFlush(userMapper.userRequestToUser(userRequestDto, userFromDB));
    }

    @Override
    public ConfirmationResponseDto saveUser(RegistrationUserRequestDto registrationUserRequestDto) {
        Optional<User> userFromDB = userRepository.findByEmail(registrationUserRequestDto.email());

        if (userFromDB.isPresent()) {
            log.info("User with email {} already exists!", registrationUserRequestDto.email());
            return null;
        }

        User newUser = userMapper.userRegistrationDtoToUser(registrationUserRequestDto);
        newUser.setRoles(Set.of(new Role(2L, RoleNameEnum.ROLE_USER)));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRegistrationTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());
        newUser.setIsBlocked(true);
        userRepository.save(newUser);

        ConfirmationToken confirmationToken = new ConfirmationToken(newUser);
        confirmationTokenRepository.save(confirmationToken);

        log.info("User {} successfully saved. Confirmation token: {}", newUser, confirmationToken.getToken());

        return confirmationTokenMapper.confirmationTokenToDto(confirmationToken);
    }

    @Override
    public boolean confirmEmail(UUID token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);

        if (confirmationToken != null)
        {
            User user = userRepository.findByEmail(confirmationToken.getUser().getEmail())
                    .orElseThrow(() -> new NoEntityException("User with email " + confirmationToken.getUser().getEmail() + " not found"));
            user.setIsBlocked(false);
            userRepository.save(user);
            log.info("User {} successfully confirm email by token {}", user, confirmationToken.getToken());
            return true;
        }

        log.warn("Confirmation token {} not found", token);

        return false;
    }
}
