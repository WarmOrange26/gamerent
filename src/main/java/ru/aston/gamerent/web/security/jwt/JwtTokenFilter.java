package ru.aston.gamerent.web.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {
    public static final String BEARER = "Bearer";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        Optional.ofNullable((HttpServletRequest) req)
                .map(request -> request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> isNotBlank(token) && token.startsWith(BEARER))
                .map(token -> token.replace(BEARER, EMPTY))
                .filter(jwtTokenProvider::validateToken)
                .map(jwtTokenProvider::getAuthentication)
                .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

        filterChain.doFilter(req, res);
    }
}
