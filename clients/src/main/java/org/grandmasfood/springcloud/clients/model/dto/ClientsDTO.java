package org.grandmasfood.springcloud.clients.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientsDTO(

        @NotEmpty(message = "Name cannot be empty")
        @Size(max = 255, message = "Name and surname must be at most 255 characters")
        String name,

        @Email(message = "Email should be valid")
        @NotEmpty(message = "Email cannot be empty")
        @Column(unique = true)
        @Size(max = 255, message = "Email must be at most 255 characters")
        String email,

        @NotBlank(message = "Document cannot be empty")
        @Pattern(regexp = "^(CC|CE|P)-\\d{1,20}$", message = "Document must include type (CC, CE, P,...) followed by a hyphen and a number, with a maximum of 20 characters")
        String document,

        @NotBlank(message = "Phone cannot be empty")
        @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
        String phone,

        @NotBlank(message = "Delivery address cannot be empty")
        @Size(max = 500, message = "Delivery address must be at most 500 characters")
        String deliveryAddress,

        Boolean active

) {
    public ClientsDTO {
        active = active == null ? true : active;
    }
}