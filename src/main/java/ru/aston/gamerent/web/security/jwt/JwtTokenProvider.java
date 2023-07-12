package ru.aston.gamerent.web.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.aston.gamerent.web.security.JwtUserDetailsService;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    public static final String ROLES = "roles";

    @Value("${spring.security.jwt.secret}")
    private String secret;

    @Value("${spring.security.jwt.expired}")
    private long validityInMilliseconds;

    private final JwtUserDetailsService userDetailsService;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put(ROLES, authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                log.info(" JWT token has expired");
                return false;
            }
            log.info("JWT token is valid");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Expired or invalid JWT token");
            return false;
        }
    }
}
