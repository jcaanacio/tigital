package com.accenture.tigital.libraries.implementations;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.accenture.tigital.libraries.enums.UserRole;
import com.accenture.tigital.libraries.interfaces.ITokenization;
import com.accenture.tigital.libraries.typings.TokenizationPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtTokenization implements ITokenization {

    private final Key _key;

    public JwtTokenization() {
        this._key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    @Override
    public boolean verify(String token) {
        try {
            // Jws<Claims> claimsJws = Jwts.parserBuilder()
            //         .setSigningKey(Keys.hmacShaKeyFor(this._key))
            //         .build()
            //         .parseClaimsJws(token);

            Jwts.parserBuilder()
                    .setSigningKey(this._key)
                    .build()
                    .parseClaimsJws(token);

            // If the token is successfully parsed and verified, you can access the claims
            // Claims claims = claimsJws.getBody();
            // Perform additional verification or retrieve information from the claims

            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public String sign(TokenizationPayload payload) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 600000); // 10 minutes expiration



        Claims claims = Jwts.claims();
        claims.put("user_id", payload.getId());
        claims.put("username", payload.getUsername());
        claims.put("role", payload.getUserRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(this._key)
                .compact();
    }


    @Override
    public String refresh(TokenizationPayload payload) {
        return this.sign(payload);
    }

    @Override
    public TokenizationPayload getAllClaimsFromToken(String token) {
        Claims claim = Jwts.parserBuilder()
                    .setSigningKey(this._key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        Long userId = Long.parseLong(claim.get("user_id").toString());
        String username =  claim.get("username").toString();
        UserRole role = UserRole.valueOf(claim.get("role").toString());

        return new TokenizationPayload(userId, username, role);
    }

}
