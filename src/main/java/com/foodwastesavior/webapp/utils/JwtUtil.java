package com.foodwastesavior.webapp.utils;

import com.foodwastesavior.webapp.exception.TokenValidationException;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // set into the safe place
    // need 32 characters
    private static String secretKey;

    @Value("${SECRET_KEY_JWT}")
    public void setSecretKey(String secretKey) {
        JwtUtil.secretKey = secretKey;
    }

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // expiration => days
    public static String generateToken(String subject, long expirationTime) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000 * expirationTime)))
                .signWith(getSigningKey())
                .compact();
    }

    ////////// add exception later
    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();

        } catch (JwtException e) {
            throw new TokenValidationException("Invalid or expired Jwt Token", e);
        }

    }
}
