package ru.aston.gamerent.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.security.jwt.JwtUser;
import ru.aston.gamerent.security.jwt.JwtUserFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email:" + email + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("Email: {} successfully loaded", email);
        return jwtUser;
    }
}
