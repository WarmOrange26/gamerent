package ru.aston.gamerent.web.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.aston.gamerent.exception.BadRequestException;
import ru.aston.gamerent.model.dto.security.AuthenticationUserDto;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.util.WebUtils;
import ru.aston.gamerent.web.security.jwt.JwtTokenProvider;
import java.util.List;
import java.util.Set;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class OAuth2GoogleUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        List<SimpleGrantedAuthority> authorities = Set.of(RoleNameEnum.ROLE_USER).stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .toList();

        DefaultOidcUser oidcUser = new DefaultOidcUser(authorities, userRequest.getIdToken());

        addJwtTokenToResponse(oidcUser);

        return oidcUser;
    }

    private void addJwtTokenToResponse(DefaultOidcUser oidcUser) {
        String token = jwtTokenProvider
                .createToken(new AuthenticationUserDto(oidcUser.getEmail(), Set.of(RoleNameEnum.ROLE_USER)));

        HttpServletResponse response = ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()))
                .map(ServletRequestAttributes::getResponse)
                .orElseThrow(() -> new BadRequestException("Authentication with google failed. Try again."));

        WebUtils.addCookieWithTokenToResponse(response, token);
    }
}