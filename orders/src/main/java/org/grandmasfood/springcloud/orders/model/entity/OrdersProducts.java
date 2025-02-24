package org.grandmasfood.springcloud.orders.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "orders_products")
@Data
public class OrdersProducts {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "order_id", unique = true)
    private Long productId;

    @Column(name = "product_uuid", unique = true)
    private UUID productUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrdersProducts)) return false;

        OrdersProducts obj = (OrdersProducts) o;

        return this.productId != null && this.productId.equals(obj.productId);

    }
}
