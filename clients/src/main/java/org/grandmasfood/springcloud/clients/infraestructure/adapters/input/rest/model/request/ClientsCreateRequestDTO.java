package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class ClientsCreateRequestDTO {


    private UUID uuid;
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 255, message = "Name and surname must be at most 255 characters")
    private String name;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Document cannot be empty")
    @Pattern(regexp = "^(CC|CE|P)-\\d{1,20}$", message = "Document must include type (CC, CE, P,...) followed by a hyphen and a number, with a maximum of 20 characters")
    private String document;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Delivery address cannot be empty")
    @Size(max = 500, message = "Delivery address must be at most 500 characters")
    private String deliveryAddress;


    private Boolean active;

    public ClientsCreateRequestDTO() {
        active = active == null ? true : active;
    }

}