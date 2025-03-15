package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.OrdersClients;
import org.grandmasfood.springcloud.orders.domain.model.OrdersProducts;
import org.grandmasfood.springcloud.orders.domain.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDTO {

    private UUID uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private UUID productUuid;
    private int quantity;
    private String extraInfo;
    private double subTotal;
    private double tax;
    private double grandTotal;
    private boolean delivered;
    private LocalDateTime deliveryDate;
}
