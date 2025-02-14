package org.grandmasfood.springcloud.orders.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record OrdersDTO(
        @NotEmpty(message = "UUID cannot be empty")
        String uuid,
        @NotNull(message = "Creation date and time cannot be null")
        LocalDateTime creation_date_time,
        @NotEmpty(message = "Client document cannot be empty")
        String client_document,
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
        boolean delivered,
        String delivery_date
) {
}