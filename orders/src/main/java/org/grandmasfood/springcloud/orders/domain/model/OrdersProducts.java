package org.grandmasfood.springcloud.orders.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OrdersProducts {
    private Long id;
    private Long productId;
    private UUID productUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrdersProducts)) return false;

        OrdersProducts obj = (OrdersProducts) o;

        return this.productId != null && this.productId.equals(obj.productId);

    }
}
