package org.grandmasfood.springcloud.users.infrastructure.adapters.output;

import org.grandmasfood.springcloud.users.application.ports.output.UserPersistentPort;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.mapper.UserMapper;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserPersistentAdapter implements UserPersistentPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserPersistentAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(userRepository.save(userMapper.toUserEntity(user)));
    }

}
