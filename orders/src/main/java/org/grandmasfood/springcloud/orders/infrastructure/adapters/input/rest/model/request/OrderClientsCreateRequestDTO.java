package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class OrderClientsCreateRequestDTO {
    private Long id;
    @NotEmpty(message = "ClientId cannot be empty")
    private Long clientId;
    @NotEmpty(message = "Client document cannot be empty")
    private String clientDocument;
}
