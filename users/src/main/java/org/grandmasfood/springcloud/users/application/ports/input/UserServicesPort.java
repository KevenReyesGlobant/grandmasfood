package org.grandmasfood.springcloud.users.application.ports.input;

import org.grandmasfood.springcloud.users.domain.model.User;

public interface UserServicesPort {
    User save(User user);

}
