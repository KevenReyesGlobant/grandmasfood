package org.grandmasfood.springcloud.orders.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "UUID cannot be empty")
    private String uuid;

    @NotNull(message = "Creation date and time cannot be null")
    private LocalDateTime creation_date_time;

    @NotEmpty(message = "Client document cannot be empty")
    private String client_document;

    @NotEmpty(message = "Product UUID cannot be empty")
    private String product_uuid;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    private String extra_info;

    @Positive(message = "Subtotal must be positive")
    private double sub_total;

    @Positive(message = "Tax must be positive")
    private double tax;

    @Positive(message = "Grand total must be positive")
    private double grand_total;

    private boolean delivered;

    private String delivery_date;

    private Boolean active;

    public Optional<Orders> setInactiveOrders() {
        active = false;
        return Optional.empty();
    }
}