package com.emazon.transaction.infraestructure.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static com.emazon.transaction.infraestructure.util.InfrastructureConstants.*;

@Component
public class JwtUtils {
    @Value(JWT_KEY_GENERATOR)
    private String privateKey;
    @Value(JWT_USER_GENERATOR)
    private String userGenerator;

    public String createToken(String username, String authorities) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim(AUTHORITIES, authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TIME_EXPIRATION))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }
}
