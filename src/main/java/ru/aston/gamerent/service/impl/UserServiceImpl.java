package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.request.UserRequest;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.model.dto.RegistrationUser;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.model.exception.NoEntityException;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.service.UserService;
import ru.aston.gamerent.service.mapper.UserMapper;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponse getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));
        List<Account> accounts = userRepository.findAccountsByUser(user);
        List<Wallet> wallets = userRepository.findWalletsByUser(user);
        return userMapper.userToUserResponseDto(user, wallets, accounts);
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        User userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));

        userRepository.saveAndFlush(userMapper.userRequestToUser(userRequest, userFromDB));
    }

    @Override
    public boolean saveUser(RegistrationUser registrationUser) {
        Optional<User> userFromDB = userRepository.findByEmail(registrationUser.email());
        if (userFromDB.isPresent()) {
            log.info("User with email {} already exists!", registrationUser.email());
            return false;
        }
        User newUser = userMapper.userRegistrationDtoToUser(registrationUser);
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
