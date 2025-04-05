package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
