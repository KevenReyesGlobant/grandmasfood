package org.grandmasfood.springcloud.users.infrastructure.adapters.output.mapper;

import org.grandmasfood.springcloud.users.domain.model.User;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
