package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientsResponseDTO {

    private Long id;
    private UUID uuid;
    private String name;
    private String email;
    private String document;
    private String phone;
    private String deliveryAddress;
    private Boolean active;

}