package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.grandmasfood.springcloud.orders.domain.model.Client;
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
    private Long idOrder;

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

    private UUID uuid;

    @NotNull(message = "Creation date and time cannot be null")
    private LocalDateTime creationDateTime;

    @NotEmpty(message = "Client document cannot be empty")
    private String clientDocument;

    @NotNull(message = "Product UUID cannot be null")
    private UUID productUuid;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 99, message = "Quantity must be less than 100")
    private int quantity;

    @Size(max = 511, message = "Product extra info must be at most 511 characters")
    private String extraInfo;

    private double subTotal;
    private double tax;
    private double grandTotal;

    private boolean delivered;

    private LocalDateTime deliveryDate;

    @JsonIgnore
    private Boolean active;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}