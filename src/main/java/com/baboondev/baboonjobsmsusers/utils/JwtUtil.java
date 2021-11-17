package com.baboondev.baboonjobsmsusers.utils;

import java.util.Date;

import com.baboondev.baboonjobsmsusers.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private static final String JWT_SECRET = "JWT_SECRET";

    public static String generateToken(User user) {
        return Jwts
                .builder()
                .claim("id", user.get_id())
                .claim("role", user.getRole().getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes()).compact();
    }
}
