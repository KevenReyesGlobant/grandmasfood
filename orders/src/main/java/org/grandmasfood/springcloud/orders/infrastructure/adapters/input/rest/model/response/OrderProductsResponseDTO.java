package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductsResponseDTO {
    private Long id;
    private Long productId;
    private UUID productUuid;
}
