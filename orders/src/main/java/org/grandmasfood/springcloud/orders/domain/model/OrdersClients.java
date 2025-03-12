package org.grandmasfood.springcloud.orders.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrdersClients {
    private Long id;
    private Long clientId;
    private String clientDocument;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrdersClients)) return false;

        OrdersClients obj = (OrdersClients) o;

        return this.clientId != null && this.clientId.equals(obj.clientId);

    }
}
