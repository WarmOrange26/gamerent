package ru.aston.gamerent.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.aston.gamerent.service.impl.AppOAuth2UserService;
import ru.aston.gamerent.service.impl.OAuth2Service;
import ru.aston.gamerent.web.security.jwt.JwtTokenFilterConfigurer;
import ru.aston.gamerent.web.security.jwt.JwtTokenProvider;
import ru.aston.gamerent.web.security.oauth.AppOAuth2User;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    private final AppOAuth2UserService appOAuth2UserService;

    private final OAuth2Service oAuth2Service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers("/", "/index", "/registration/", "/verify/**").permitAll()
                        .requestMatchers("/", "/oauth/**").permitAll()
                )
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(appOAuth2UserService))
                        .successHandler((request, response, authentication) -> {
                            AppOAuth2User oauthUser = (AppOAuth2User) authentication.getPrincipal();
                            oAuth2Service.processOAuthPostLogin(oauthUser.getEmail());
                            response.sendRedirect("/");
                        }))
                .httpBasic(Customizer.withDefaults())
                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


