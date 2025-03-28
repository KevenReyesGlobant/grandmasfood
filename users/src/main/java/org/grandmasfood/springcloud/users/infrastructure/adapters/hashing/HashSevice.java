package org.grandmasfood.springcloud.users.infrastructure.adapters.hashing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashSevice implements HashServicesPort {

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
