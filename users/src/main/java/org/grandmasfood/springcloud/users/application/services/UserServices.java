package org.grandmasfood.springcloud.users.application.services;

import org.grandmasfood.springcloud.users.application.ports.input.UserServicesPort;
import org.grandmasfood.springcloud.users.application.ports.output.UserPersistentPort;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserServicesPort, UserDetailsService {

    private final UserPersistentPort userPersistentPort;

    public UserServices(UserPersistentPort userPersistentPort) {
        this.userPersistentPort = userPersistentPort;
    }

    @Override
    public User save(User user) {

        if (user != null) {

            return userPersistentPort.save(user);
        }

        throw new IllegalArgumentException("User cannot be null");

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email == null || email.isEmpty()) {
            throw new UsernameNotFoundException("Email cannot be null or empty");
        }

        return userPersistentPort.loadUserByUsername(email);
    }
}
