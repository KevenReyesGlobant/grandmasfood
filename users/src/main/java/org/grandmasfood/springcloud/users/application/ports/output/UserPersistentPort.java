package org.grandmasfood.springcloud.users.application.ports.output;

import org.grandmasfood.springcloud.users.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserPersistentPort {

    User save(User user);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    Optional<User> findByEmail(String email);

}
