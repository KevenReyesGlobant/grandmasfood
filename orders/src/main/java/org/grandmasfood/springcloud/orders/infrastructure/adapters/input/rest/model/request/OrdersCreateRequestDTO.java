package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Creation date and time cannot be null")
    LocalDateTime creationDateTime;

    //    @NotEmpty(message = "Client document cannot be empty")
    String clientDocument;

    List<OrdersProductsEntity> ordersProducts;

    List<OrdersClientsEntity> ordersClients;

    //    @NotEmpty(message = "Product UUID cannot be empty")
    UUID productUuid;

    @Positive(message = "Quantity must be positive")
    int quantity;

    @NotEmpty(message = "Product UUID cannot be empty")
    String extraInfo;

    double subTotal;

    double tax;

    double grandTotal;

    Boolean delivered;

    //    @NotEmpty(message = "Delivered Date status cannot be empty")
    LocalDateTime deliveryDate;

    Boolean active;

    public OrdersCreateRequestDTO() {
        active = active == null ? true : active;
        delivered = delivered == null ? false : delivered;
        deliveryDate = Boolean.FALSE.equals(delivered) ? null : deliveryDate;
    }
}
