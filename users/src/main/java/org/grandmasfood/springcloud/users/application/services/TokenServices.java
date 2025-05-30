package org.grandmasfood.springcloud.users.application.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.grandmasfood.springcloud.users.application.ports.input.TokenServicesPort;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenServices implements TokenServicesPort {

    @Value("${jwt.secret}")
    private String apiSecret;

    private final Set<String> invalidatedTokens = ConcurrentHashMap.newKeySet();


    @Override
    public String generatedToken(User registerUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("grandmasfood")
                    .withSubject(registerUser.getEmail())
                    .withClaim("id", registerUser.getIdUser())
                    .withExpiresAt(generateExpiration())
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }
    }

    @Override
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token is null");
        }

        if (isTokenInvalidated(token)) {
            throw new RuntimeException("Token has been invalidated");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("grandmasfood")
                    .build()
                    .verify(token);

            if (verifier.getSubject() == null) {
                throw new RuntimeException("Invalid token: subject is null");
            }

            return verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token", exception);
        }
    }

    @Override
    public void invalidatedToken(String token) {
        if (token != null && !token.isEmpty()) {
            String cleanToken = token.replace("Bearer ", "");
            invalidatedTokens.add(cleanToken);
        }
    }

    @Override
    public boolean isTokenInvalidated(String token) {
        if (token == null || token.isEmpty()) {
            return true;
        }
        String cleanToken = token.replace("Bearer ", "");
        return invalidatedTokens.contains(cleanToken);
    }

    @Override
    public Instant generateExpiration() {
        return LocalDateTime.now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-05:00"));
    }
}
