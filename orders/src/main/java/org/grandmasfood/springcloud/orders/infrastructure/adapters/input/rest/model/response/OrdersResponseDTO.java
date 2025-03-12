package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.orders.model.Client;
import org.grandmasfood.springcloud.orders.model.Product;
import org.grandmasfood.springcloud.orders.model.entity.OrdersClients;
import org.grandmasfood.springcloud.orders.model.entity.OrdersProducts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDTO {
    private Long id;
    private List<OrdersProducts> ordersProductsList;
    private List<Product> productList;
    private List<Client> clientList;
    private List<OrdersClients> ordersClientsList;
    private UUID uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUuid;
    private int quantity;
    private String extraInfo;
    private double subTotal;
    private double tax;
    private double grandTotal;
    private boolean delivered;
    private String deliveryDate;
    private Boolean active;
}
