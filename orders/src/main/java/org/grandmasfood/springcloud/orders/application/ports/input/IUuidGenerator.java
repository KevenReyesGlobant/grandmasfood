package org.grandmasfood.springcloud.orders.application.ports.input;
import java.util.UUID;

public interface IUuidGenerator {
    UUID generateUuid();
}
