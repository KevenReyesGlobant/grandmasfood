package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientsResponseDTO {

    private String name;
    private String email;
    private String document;
    private String phone;
    private String deliveryAddress;
}