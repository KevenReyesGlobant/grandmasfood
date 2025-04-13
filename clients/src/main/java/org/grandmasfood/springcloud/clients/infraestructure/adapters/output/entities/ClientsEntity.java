package org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "ClientsEntity")
@Table(name = "clients_entity")
@NoArgsConstructor
@AllArgsConstructor
public class ClientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID uuid;

    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 255, message = "Name and surname must be at most 255 characters")
    private String name;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @Column(unique = true, length = 24)
    @NotBlank(message = "Document cannot be empty")
    @Pattern(regexp = "^(CC|CE|P)-\\d{1,20}$", message = "Document must include type (CC, CE, P,...) followed by a hyphen and a number, with a maximum of 20 characters")
    private String document;

    @NotBlank(message = "Phone cannot be empty")
    @Column(length = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Delivery address cannot be empty")
    @Size(max = 500, message = "Delivery address must be at most 500 characters")
    private String deliveryAddress;

    private Boolean active;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}