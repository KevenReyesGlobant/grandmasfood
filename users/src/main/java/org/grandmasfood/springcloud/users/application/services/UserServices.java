package org.grandmasfood.springcloud.users.application.services;

import org.grandmasfood.springcloud.users.application.ports.input.UserServicesPort;
import org.grandmasfood.springcloud.users.application.ports.output.UserPersistentPort;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserServicesPort {

    private final UserPersistentPort userPersistentPort;

    public UserServices(UserPersistentPort userPersistentPort) {
        this.userPersistentPort = userPersistentPort;
    }

    @Override
    public User save(User user) {
        return userPersistentPort.save(user);
    }
}
