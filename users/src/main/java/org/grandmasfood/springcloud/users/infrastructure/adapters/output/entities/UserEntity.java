package org.grandmasfood.springcloud.users.infrastructure.adapters.output.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.users.domain.model.enums.RoleUser;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name="UserEntity")
@Table(name = "user_entity")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private UUID uuid;
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 100, message = "First Name must be at most 255 characters")
    private String firstName;
    @Size(max = 100, message = "Last Name must be at most 255 characters")
    private String lastName;
    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;
    private Boolean active;
    private String verification;
    private LocalDateTime token_expiry;
    private Boolean email_verified;

    public void verifyEmail() {
        this.email_verified = true;
    }


    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
