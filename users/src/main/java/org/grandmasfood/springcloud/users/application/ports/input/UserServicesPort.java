package org.grandmasfood.springcloud.users.application.ports.input;

import org.grandmasfood.springcloud.users.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserServicesPort {

    User save(User user);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
