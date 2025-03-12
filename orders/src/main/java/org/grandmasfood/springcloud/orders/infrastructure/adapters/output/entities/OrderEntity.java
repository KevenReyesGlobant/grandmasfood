package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.OrdersClients;
import org.grandmasfood.springcloud.orders.domain.model.OrdersProducts;
import org.grandmasfood.springcloud.orders.domain.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders_entity")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrdersProductsEntity> ordersProducts;

    @Transient
    private List<Product> product;

    @Transient
    private List<Client> client;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrdersClientsEntity> ordersClients;

    //    @NotNull(message = "UUID cannot be null")
    private UUID uuid;

    @NotNull(message = "Creation date and time cannot be null")
    private LocalDateTime creationDateTime;

    //    @NotEmpty(message = "Client document cannot be empty")
    private String clientDocument;

    @NotEmpty(message = "Product UUID cannot be empty")
    private String productUuid;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @NotEmpty(message = "Product extra info cannot be empty")
    private String extraInfo;

    @Positive(message = "Subtotal must be positive")
    private double subTotal;

    @Positive(message = "Tax must be positive")
    private double tax;

    @Positive(message = "Grand total must be positive")
    private double grandTotal;

    private boolean delivered;

    private LocalDateTime deliveryDate;

    @JsonIgnore
    private Boolean active;
}
