package ru.aston.gamerent.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.dto.AuthenticationRequestDto;
import ru.aston.gamerent.dto.AuthenticationUserDto;
import ru.aston.gamerent.mapper.AuthenticationUserMapper;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationUserMapper authenticationUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.email());
        AuthenticationUserDto authenticationUserDto = authenticationUserMapper.userToAuthenticationUserDto(user);
        if (user != null && (passwordEncoder.matches(requestDto.password(), user.getPassword()))) {
                log.info("User {} found by email: {}", authenticationUserDto, authenticationUserDto.email());
                return authenticationUserDto;
        }
        log.info("Invalid username or password");
        return null;
    }
}
