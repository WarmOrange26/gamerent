package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.exception.NoEntityException;
import ru.aston.gamerent.service.UserService;
import ru.aston.gamerent.service.mapper.UserMapper;
import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUserById(long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserResponseDto)
                .orElseThrow(() -> new NoEntityException("User with id " + id + " was not found"));
    }
}
