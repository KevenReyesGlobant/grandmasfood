package model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record ClientDTO(
        @NotEmpty(message = "UUID cannot be empty")
        String uuid,
        @NotEmpty(message = "This field is not empty")
        String name,
        @NotEmpty(message = "Email cannot be empty")
        @Email(message = "Email should be valid")
        @Column(unique = true)
        String email,
        @NotEmpty(message = "Document cannot be empty")
        String document,
        @NotEmpty(message = "Phone cannot be empty")
        String phone,
        @NotEmpty(message = "Delivery address cannot be empty")
        String deliveryAddress) {
}
