package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderClientsResponseDTO {
    private Long id;
    private Long clientId;
    private String clientDocument;
}
