package com.raphael.springsecurity.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String secretKey;

    // TODO: Generate token
    public String generateToken(String username) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // TODO: To validate token
    // TODO: 1. Get all claims from the token
    // TODO: 2. Get username/email/subject from the claims
    // TODO: 3.  check that the token's expiration date is NOT before the current date
    // TODO: 4. check that the username from the token is equal to the current username
    // TODO: 5. For token to be valid, both 3 & 4 must be satisfied

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final boolean tokenIsNotExpired = !getClaims(token).getExpiration().before(new Date());
        final boolean username = getUsername(token).equals(userDetails.getUsername());
        return tokenIsNotExpired && username;
    }
//
//    public boolean isToken(String token, UserDetails userDetails) {
//
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        final String username =  claims.getSubject();
//        final boolean isTokenExpired = ! claims.getExpiration().before(new Date());
//        if (username.equals(userDetails.getUsername()) && !isTokenExpired) return username;
//        else return null;
//    }
}
