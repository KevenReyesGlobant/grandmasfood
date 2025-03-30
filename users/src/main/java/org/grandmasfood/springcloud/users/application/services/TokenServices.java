package org.grandmasfood.springcloud.users.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.grandmasfood.springcloud.users.application.ports.input.TokenServicesPort;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public class TokenServices implements TokenServicesPort {
    @Value("${spring.security.secret}")
    private String apiSecret;
    @Override
    public String generatedToken(User registerUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("airport")
                    .withSubject(registerUser.getEmail())
                    .withClaim("id", registerUser.getIdUser())
                    .withExpiresAt(generateExpiration())
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }
    }

    @Override
    public String getSubject(String token) {
        return "";
    }

    @Override
    public void invalidateToken(String token) {

    }

    @Override
    public boolean isTokenInvalidated(String token) {
        return false;
    }

    @Override
    public Instant generateExpiration() {
        return null;
    }
}
