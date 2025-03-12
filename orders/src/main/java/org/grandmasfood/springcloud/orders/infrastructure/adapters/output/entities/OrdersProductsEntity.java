package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "orders_products_entity")
@Data
public class OrdersProductsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "order_id", unique = true)
    private Long productId;

    @Column(name = "product_uuid", unique = true)
    private UUID productUuid;
    
}
