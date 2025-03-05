package org.grandmasfood.springcloud.clients.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {


    private Long id;

    private UUID uuid;

    private String name;

    private String email;

    private String document;

    private String phone;

    private String deliveryAddress;
    private Boolean active;

}
