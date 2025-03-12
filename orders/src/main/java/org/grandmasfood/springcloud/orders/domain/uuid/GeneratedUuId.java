package org.grandmasfood.springcloud.orders.domain.uuid;

import org.grandmasfood.springcloud.orders.application.ports.input.IUuidGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeneratedUuId implements IUuidGenerator {

    @Override
    public UUID generateUuid() {
        return UUID.randomUUID();
    }
}
