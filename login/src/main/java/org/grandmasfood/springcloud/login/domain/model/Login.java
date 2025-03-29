package org.grandmasfood.springcloud.login.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private Long idLogin;
    private String email;
    private String password;
    private Boolean active;
}
