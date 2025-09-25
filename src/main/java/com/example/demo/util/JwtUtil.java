package com.example.demo.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private static final SecretKey SECRET = Jwts.SIG.HS512.key().build();
        
    private static final long EXPIRATION_TIME = 86400000;

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return parsedClaimJws(token).getPayload().getSubject();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        Date expiration = parsedClaimJws(token).getPayload().getExpiration();
        return expiration.before(new Date());
    }

    private Jws<Claims> parsedClaimJws(String token) {
        return Jwts.parser().verifyWith(SECRET).build().parseSignedClaims(token);
    }
}