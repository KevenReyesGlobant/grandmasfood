package org.grandmasfood.springcloud.users.application.ports.output;

import org.grandmasfood.springcloud.users.domain.model.User;

public interface UserPersistentPort {
    User save(User user);
}
