package org.grandmasfood.springcloud.users.infrastructure.adapters.output.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "UserEntity")
@Table(name = "user_entity")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private UUID uuid;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "role_user", nullable = false)
    private String roleUser;

    @Column(nullable = false)
    private Boolean active = false;

    @Column
    private String verification;

    @Column(name = "token_expiry")
    private LocalDateTime tokenExpiry;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;


    public void verifyEmail() {
        this.emailVerified = true;
    }


    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
