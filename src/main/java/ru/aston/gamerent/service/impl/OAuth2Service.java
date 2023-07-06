package ru.aston.gamerent.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.enumeration.Provider;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final UserRepository userRepository;

    public void processOAuthPostLogin(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setRoles(Set.of(new Role(2L, RoleNameEnum.ROLE_USER)));
            newUser.setPassword(new BCryptPasswordEncoder().encode(email));
            newUser.setRegistrationTime(LocalDateTime.now());
            newUser.setUpdateTime(LocalDateTime.now());
            newUser.setIsBlocked(false);
            userRepository.save(newUser);
        }
    }
}
