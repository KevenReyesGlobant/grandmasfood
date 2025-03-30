package org.grandmasfood.springcloud.users.infrastructure.adapters.hashing;

import org.grandmasfood.springcloud.users.application.ports.input.HashServicesPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashService implements HashServicesPort {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean checkPassword(String password, String hash) {
        return bCryptPasswordEncoder.matches(password, hash);
    }
}