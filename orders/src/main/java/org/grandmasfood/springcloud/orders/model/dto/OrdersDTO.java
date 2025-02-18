package org.grandmasfood.springcloud.orders.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.grandmasfood.springcloud.orders.config.interfaces.IValidDeliveryDate;
import org.grandmasfood.springcloud.orders.model.entity.OrdersClients;
import org.grandmasfood.springcloud.orders.model.entity.OrdersProducts;

import java.time.LocalDateTime;
import java.util.List;


@IValidDeliveryDate
public record OrdersDTO(
        @NotNull(message = "Creation date and time cannot be null")
        LocalDateTime creation_date_time,

        @NotEmpty(message = "Client document cannot be empty")
        String client_document,

        List<OrdersProducts> ordersProducts,

        List<OrdersClients> ordersClients,

        @NotEmpty(message = "Product UUID cannot be empty")
        String product_uuid,

        @Positive(message = "Quantity must be positive")
        int quantity,

        String extra_info,

        @Positive(message = "Subtotal must be positive")
        double sub_total,

        @Positive(message = "Tax must be positive")
        double tax,

        @Positive(message = "Grand total must be positive")
        double grand_total,

        @NotNull(message = "Delivered status cannot be null")
        Boolean delivered,

//        @NotEmpty(message = "Delivered Date status cannot be empty")
        String delivery_date,

        Boolean active
) {
    public OrdersDTO {
        active = active == null ? true : active;
        delivered = delivered == null ? false : delivered;
    }


}