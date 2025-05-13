package org.grandmasfood.springcloud.users.infrastructure.adapters.input.rest.model.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.grandmasfood.springcloud.users.domain.model.enums.RoleUser;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class UserCreateRequestDTO {


    private UUID uuid;
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 100, message = "First Name must be at most 255 characters")
    private String firstName;
    @Size(max = 100, message = "Last Name must be at most 255 characters")
    private String lastName;
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$", message = "The password must be between 8 and 20 characters long, contain at least one uppercase letter, one lowercase letter, and one number")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;
    private Boolean active;
    private String verification;
    private LocalDateTime tokenExpiry;
    private Boolean emailVerified;

    public UserCreateRequestDTO() {
        emailVerified = emailVerified == null ? false : emailVerified;
        active = active == null ? true : active;
        roleUser = roleUser == null ? RoleUser.client : roleUser;
    }

}
