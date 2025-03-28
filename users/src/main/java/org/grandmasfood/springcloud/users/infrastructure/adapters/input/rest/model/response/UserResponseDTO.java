package org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.users.domain.model.enums.RoleUser;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
