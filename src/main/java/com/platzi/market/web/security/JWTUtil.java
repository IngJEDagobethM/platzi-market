package com.platzi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@Component
public class JWTUtil {
    private static final String KEY = secretKeyFor(SignatureAlgorithm.HS256).toString();
    // https://developer.okta.com/blog/2018/10/31/jwts-with-java **New
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(KEY);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                //.signWith(SignatureAlgorithm.HS256, KEY.getBytes())
                .signWith(signingKey, signatureAlgorithm) // **New
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(apiKeySecretBytes) // **New
                //.setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
