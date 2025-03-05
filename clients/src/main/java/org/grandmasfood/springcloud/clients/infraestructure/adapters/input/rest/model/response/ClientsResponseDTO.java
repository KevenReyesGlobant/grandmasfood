package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientsResponseDTO {


    private String name;

    private String email;

    private String document;

    private String phone;

    private String deliveryAddress;


    Boolean active;

    public ClientsResponseDTO() {
        active = active == null ? true : active;
    }
}