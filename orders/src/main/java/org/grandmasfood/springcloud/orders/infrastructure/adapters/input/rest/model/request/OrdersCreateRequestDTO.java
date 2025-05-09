package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.grandmasfood.springcloud.orders.application.ports.input.IValidDeliveryDate;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrdersClientsEntity;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrdersProductsEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@IValidDeliveryDate
public class OrdersCreateRequestDTO {

    LocalDateTime creationDateTime;
    String clientDocument;
    List<OrdersProductsEntity> ordersProducts;
    List<OrdersClientsEntity> ordersClients;
    UUID productUuid;
    @Positive(message = "Quantity must be positive")
    int quantity;
    @NotEmpty(message = "Product UUID cannot be empty")
    String extraInfo;
    double subTotal;
    double tax;
    double grandTotal;
    Boolean delivered;
    LocalDateTime deliveryDate;
    Boolean active;

    public OrdersCreateRequestDTO() {
        active = active == null ? true : active;
        delivered = delivered == null ? false : delivered;
        deliveryDate = Boolean.FALSE.equals(delivered) ? null : deliveryDate;
        creationDateTime = LocalDateTime.now();
    }
}
