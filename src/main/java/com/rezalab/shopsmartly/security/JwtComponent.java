package com.rezalab.shopsmartly.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class JwtComponent {
    private SecretKey key;

    public JwtComponent() { key = Keys.secretKeyFor(SignatureAlgorithm.HS256); }

    public String generateToken(Duration duration, String id) {
        LocalDateTime expiredDate = LocalDateTime.now().plusSeconds(duration.getSeconds());
        JwtBuilder jwtBuilder = Jwts.builder().signWith(key).setExpiration(Timestamp.valueOf(expiredDate)).setIssuer(id);
        return jwtBuilder.compact();
    }

    public String getClaimId(String token) {
        String res = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getIssuer();
        return res;
    }
}
