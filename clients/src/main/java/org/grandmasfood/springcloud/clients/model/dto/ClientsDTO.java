package org.grandmasfood.springcloud.clients.model.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public record ClientsDTO(
        @NotEmpty(message = "UUID cannot be empty")
        String uuid,

        @NotEmpty(message = "Name cannot be empty")
        String name,

        @Email(message = "Email should be valid")
        @NotEmpty(message = "Email cannot be empty")
        String email,

        @NotBlank(message = "Document cannot be empty")
        String document,

        @NotBlank(message = "Phone cannot be empty")
        String phone,

        @NotBlank(message = "Delivery address cannot be empty")
        String deliveryAddress
) {


}
