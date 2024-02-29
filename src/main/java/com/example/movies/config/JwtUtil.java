package com.example.movies.config;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;




@Component
public class JwtUtil {
    private static final String SECRET = "c5434f8f862a6aa7bc51bc8e6a9c7b66c21f6c0d8255aa3e6ed81bd2165f98b99e3a5d3a936c8f899276a6dd4854da998bc3e41635b2d72aa98ac7c0120073b5";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(String username, Long id) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


}
