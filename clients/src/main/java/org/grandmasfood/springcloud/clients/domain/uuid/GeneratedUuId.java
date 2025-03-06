package org.grandmasfood.springcloud.clients.domain.uuid;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GeneratedUuId implements IUuidGenerator {

    @Override
    public UUID generateUuid() {
        return UUID.randomUUID();
    }
}
