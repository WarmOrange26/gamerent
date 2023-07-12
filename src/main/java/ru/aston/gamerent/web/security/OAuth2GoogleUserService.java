package ru.aston.gamerent.web.security;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.aston.gamerent.exception.BadRequestException;
import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.util.WebUtils;
import ru.aston.gamerent.web.security.jwt.JwtTokenProvider;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class OAuth2GoogleUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        User user = userRepository.findByEmail(userRequest.getIdToken().getEmail())
                .orElseGet(() -> userRepository.save(convertTokenToUser(userRequest.getIdToken())));

        List<SimpleGrantedAuthority> authorities = convertUserRolesToAuthorities(user);
        DefaultOidcUser oidcUser = new DefaultOidcUser(authorities, userRequest.getIdToken());

        addJwtTokenToResponse(oidcUser);

        return oidcUser;
    }

    private void addJwtTokenToResponse(DefaultOidcUser oidcUser) {
        String token = jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken(
                oidcUser.getIdToken().getEmail(),
                "",
                oidcUser.getAuthorities())
        );

        HttpServletResponse response = ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()))
                .map(ServletRequestAttributes::getResponse)
                .orElseThrow(() -> new BadRequestException("Authentication with google failed. Try again."));

        WebUtils.addCookieWithTokenToResponse(response, token);
    }

    private List<SimpleGrantedAuthority> convertUserRolesToAuthorities(User user) {
        return Optional.of(user)
                .map(User::getRoles)
                .stream()
                .flatMap(Collection::stream)
                .map(Role::getName)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    private User convertTokenToUser(OidcIdToken token) {
        return User.builder()
                .username(token.getGivenName())
                .email(token.getEmail())
                .password(UUID.randomUUID().toString())
                .firstName(token.getGivenName())
                .lastName(token.getFamilyName())
                .registrationTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isBlocked(false)
                .roles(Set.of(Role.builder().id(1L).name(RoleNameEnum.ROLE_USER).build()))
                .build();
    }
}