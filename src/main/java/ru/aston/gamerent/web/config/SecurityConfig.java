package ru.aston.gamerent.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.aston.gamerent.util.WebUtils;
import ru.aston.gamerent.web.security.OAuth2GoogleUserService;
import ru.aston.gamerent.web.security.jwt.JwtTokenFilterConfigurer;
import ru.aston.gamerent.web.security.jwt.JwtTokenProvider;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String GAMES = "/games";
    public static final String LOGIN = "/login";
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuth2GoogleUserService oAuth2GoogleUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/css/**", "/js/**", "/fonts/**", "/images/**", "img/**").permitAll()
                        .requestMatchers("/", "/index", "/registration/**", LOGIN).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage(LOGIN)
                        .successHandler(authenticationSuccessHandler()))
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl(LOGIN)
                        .deleteCookies(ACCESS_TOKEN))
                .oauth2Login(config -> config
                        .loginPage(LOGIN)
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oAuth2GoogleUserService))
                        .defaultSuccessUrl(GAMES))

                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
        return http.build();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String token = jwtTokenProvider.createToken(authentication);
            WebUtils.addCookieWithTokenToResponse(response, token);
            response.sendRedirect(GAMES);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}