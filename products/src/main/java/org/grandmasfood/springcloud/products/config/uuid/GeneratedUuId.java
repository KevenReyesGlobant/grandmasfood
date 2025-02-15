package org.grandmasfood.springcloud.products.config.uuid;

import org.grandmasfood.springcloud.products.config.interfaces.IUuidGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeneratedUuId implements IUuidGenerator {

    @Override
    public UUID generateUuid() {
        return UUID.randomUUID();
    }
}
