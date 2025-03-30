package org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.controller;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.users.application.ports.input.UserServicesPort;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.request.UserCreateRequestDTO;
import org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.response.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserServicesPort userServicesPort;
    private final UserRestMapper userRestMapper;

    public UserController(UserServicesPort userServicesPort, UserRestMapper userRestMapper) {
        this.userServicesPort = userServicesPort;
        this.userRestMapper = userRestMapper;
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserResponseDTO> createProduct(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRestMapper.toUserResponseDTO(userServicesPort.save(userRestMapper.toUser(userCreateRequestDTO))));
    }
}
