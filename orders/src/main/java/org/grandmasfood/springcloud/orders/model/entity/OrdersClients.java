package org.grandmasfood.springcloud.orders.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders_clients")
@Data
public class OrdersClients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id", unique = true)
    private Long clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrdersClients)) return false;

        OrdersClients obj = (OrdersClients) o;

        return this.clientId != null && this.clientId.equals(obj.clientId);

    }
}
