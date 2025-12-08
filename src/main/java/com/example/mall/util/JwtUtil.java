package com.example.mall.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    // 原始密钥字符串
    private static final String SECRET = "thisIsAVerySecureKeyForJwtToken1234567890";

    // 密钥对象
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Long userId, String userName) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("username", userName)
                .setIssuedAt(new Date())
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Long getUserId(String token) {
        try {
            return ((Integer) Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("userId")).longValue();
        } catch (Exception e) {
            return null;
        }
    }
}
