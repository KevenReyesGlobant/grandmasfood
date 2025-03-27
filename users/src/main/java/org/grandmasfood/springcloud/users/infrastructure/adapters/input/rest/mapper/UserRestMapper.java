package org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.mapper;

import org.grandmasfood.springcloud.users.domain.model.User;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.request.UserCreateRequestDTO;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.response.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper {

    User toUser(UserCreateRequestDTO userCreateRequestDTO);

    UserResponseDTO toUserResponseDTO(User user);
}
