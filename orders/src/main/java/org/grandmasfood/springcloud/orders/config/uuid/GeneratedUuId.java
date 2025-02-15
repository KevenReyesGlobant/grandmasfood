package org.grandmasfood.springcloud.orders.config.uuid;


import org.grandmasfood.springcloud.orders.config.interfaces.IUuidGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeneratedUuId implements IUuidGenerator {

    @Override
    public UUID generateUuid() {
        return UUID.randomUUID();
    }
}
