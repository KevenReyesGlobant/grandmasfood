package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class OrderProductsCreateRequestDTO {
    private Long id;
    @NotEmpty(message = "ProductId cannot be empty")
    private Long productId;
    @NotEmpty(message = "Product UUID cannot be empty")
    private UUID productUuid;

}
