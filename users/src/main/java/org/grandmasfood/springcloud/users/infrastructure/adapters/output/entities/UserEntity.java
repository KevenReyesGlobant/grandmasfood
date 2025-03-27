package org.grandmasfood.springcloud.users.infrastructure.adapters.output.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.users.domain.model.enums.RoleUser;

import java.util.UUID;

@Data
@Entity
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
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$", message = "The password must be between 8 and 20 characters long, contain at least one uppercase letter, one lowercase letter, and one number")
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;
    private Boolean verified;
    private Boolean active;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
