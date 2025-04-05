package org.grandmasfood.springcloud.orders.domain.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersProducts {
    private Long id;
    private Long productId;
    private UUID productUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof OrdersProducts))
            return false;

        OrdersProducts obj = (OrdersProducts) o;

        return this.productId != null && this.productId.equals(obj.productId);

    }
}
