package org.grandmasfood.springcloud.users.application.ports.input;

import org.grandmasfood.springcloud.users.domain.model.User;

import java.time.Instant;

public interface TokenServicesPort {

    String generatedToken(User registerUser);

    String getSubject(String token);

    void invalidateToken(String token);

    boolean isTokenInvalidated(String token);

    Instant generateExpiration();


}
