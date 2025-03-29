package org.grandmasfood.springcloud.users.infrastructure.adapters.output;

import org.grandmasfood.springcloud.users.application.ports.output.UserPersistentPort;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.grandmasfood.springcloud.users.infrastructure.adapters.hashing.HashService;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.mapper.UserMapper;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserPersistentAdapter implements UserPersistentPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserPersistentAdapter(UserRepository userRepository, UserMapper userMapper, HashService hashService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public User save(User user) {
        user.setPassword(hashService.hashPassword(user.getPassword()));
        return userMapper.toUser(userRepository.save(userMapper.toUserEntity(user)));
    }

}
