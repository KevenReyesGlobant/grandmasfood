package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientsResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String document;
    private String phone;
    private String deliveryAddress;
    private Boolean active;

    public ClientsResponseDTO() {
        active = active == null ? true : active;
    }
}