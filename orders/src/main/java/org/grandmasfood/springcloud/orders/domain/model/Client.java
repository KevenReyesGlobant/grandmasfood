package org.grandmasfood.springcloud.orders.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class Client {
    Long id;
    UUID uuid;
    String name;
    String email;
    String document;
    String phone;
    String deliveryAddress;
    Boolean active;
}
