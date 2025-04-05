package org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginRequestDTO {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
