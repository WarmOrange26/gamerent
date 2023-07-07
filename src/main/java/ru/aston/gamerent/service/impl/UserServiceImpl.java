package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.repository.AccountRepository;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.repository.WalletRepository;
import ru.aston.gamerent.service.UserService;
import ru.aston.gamerent.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
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
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(Long id, UserRequestDto userRequestDto) {
        User userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));

        userRepository.saveAndFlush(userMapper.userRequestToUser(userRequestDto, userFromDB));
    }

    @Override
    public boolean saveUser(RegistrationUserRequestDto registrationUserRequestDto) {
        Optional<User> userFromDB = userRepository.findByEmail(registrationUserRequestDto.email());

        if (userFromDB.isPresent()) {
            log.info("User with email {} already exists!", registrationUserRequestDto.email());
            return false;
        }

        User newUser = userMapper.userRegistrationDtoToUser(registrationUserRequestDto);
        newUser.setRoles(Set.of(new Role(2L, RoleNameEnum.ROLE_USER)));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRegistrationTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());
        newUser.setIsBlocked(false);
        userRepository.save(newUser);

        log.info("User {} successfully saved", newUser);

        return true;
    }
}
