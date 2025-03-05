package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientsCreateResponseDTO {


    private String name;

    private String email;

    private String document;

    private String phone;

    private String deliveryAddress;


    Boolean active;

    public ClientsCreateResponseDTO() {
        active = active == null ? true : active;
    }
}