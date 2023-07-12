package ru.aston.gamerent.web.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {
    public static final String ACCESS_TOKEN = "access_token";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        Optional.ofNullable((HttpServletRequest) req)
                .map(HttpServletRequest::getCookies)
                .stream()
                .flatMap(Arrays::stream)
                .filter(cookie -> cookie.getName().equals(ACCESS_TOKEN))
                .findFirst()
                .map(Cookie::getValue)
                .filter(jwtTokenProvider::validateToken)
                .map(jwtTokenProvider::getAuthentication)
                .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

        filterChain.doFilter(req, res);
    }
}
