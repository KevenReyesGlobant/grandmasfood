package org.grandmasfood.springcloud.clients.domain.model.dto;

public record ClientsResponse(

        String name,
        String email,
        String document,
        String phone,
        String deliveryAddress

) {
}
