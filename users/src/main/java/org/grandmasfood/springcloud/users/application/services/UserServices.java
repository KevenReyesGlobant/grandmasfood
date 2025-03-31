package org.grandmasfood.springcloud.users.application.services;

import org.grandmasfood.springcloud.users.application.ports.input.UserServicesPort;
import org.grandmasfood.springcloud.users.application.ports.output.UserPersistentPort;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.grandmasfood.springcloud.users.infrastructure.adapters.config.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserServicesPort {

    private final UserPersistentPort userPersistentPort;
    private final EmailSender emailSender;

    public UserServices(UserPersistentPort userPersistentPort, EmailSender emailSender) {
        this.userPersistentPort = userPersistentPort;
        this.emailSender = emailSender;
    }

    @Override
    public User save(User user) {

        return userPersistentPort.save(user);
    }
}
