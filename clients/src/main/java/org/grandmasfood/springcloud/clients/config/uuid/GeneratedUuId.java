package org.grandmasfood.springcloud.clients.config.uuid;


import org.grandmasfood.springcloud.clients.config.interfaces.IUuidGenerator;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GeneratedUuId implements IUuidGenerator {

    @Override
    public UUID generateUuid() {
        return UUID.randomUUID();
    }
}
