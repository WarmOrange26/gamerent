package ru.aston.gamerent.web.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.web.security.jwt.JwtUserFactory;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .map(JwtUserFactory::create)
                .orElseThrow(() -> new UsernameNotFoundException("User with email:" + email + "not found"));
    }
}
