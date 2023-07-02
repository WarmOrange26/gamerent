package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.request.UserRequest;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.model.exception.NoEntityException;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.service.UserService;
import ru.aston.gamerent.service.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));
        List<Account> accounts = userRepository.findAccountsByUser(user);
        List<Wallet> wallets = userRepository.findWalletsByUser(user);
        return userMapper.userToUserResponseDto(user, wallets, accounts);
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        User userFromRequest = userMapper.userRequestToUser(userRequest);
        User userFromDB = userRepository.findById(id).orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));
        userFromRequest.setId(id);
        userFromRequest.setRegistrationTime(userFromDB.getRegistrationTime());
        userFromRequest.setUpdateTime(LocalDateTime.now());
        userFromRequest.setIsBlocked(userFromDB.getIsBlocked());
        userFromRequest.setRoles(userFromDB.getRoles());
        userRepository.saveAndFlush(userFromRequest);
    }
}
