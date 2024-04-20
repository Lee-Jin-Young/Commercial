package com.hanghea99.commercial.utilAndSecurity.secure;

import com.hanghea99.commercial.member.dto.MemberLoginDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

@Slf4j // 로그
@Component
public class Jwt {
    private final Key key;
    private final long accessTokenExpTime;

    // 생성자
    public Jwt(@Value("${jwt.secret}") String secretKey,
               @Value("${jwt.expiration_time") long accessTokenExpTime
    ) {
        byte[] keyButes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyButes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    // Access Token 생성
    public String createAccessToken(MemberLoginDto member) {
        return createTocken(member, accessTokenExpTime);
    }

    private String createTocken(MemberLoginDto member, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("memberId", member.getMemberId());
        claims.put("email", member.getEmail());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256) //SHA-256으로 보안
                .compact();
    }

    public Long getMemberId(String token) {
        return parseClaims(token).get("memberId", Long.class);
    }

    // JWT Token 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }

        return false;
    }

    // JWT Claims 추출
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
