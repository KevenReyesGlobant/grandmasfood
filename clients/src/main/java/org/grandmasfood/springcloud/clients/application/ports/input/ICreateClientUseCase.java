package org.grandmasfood.springcloud.clients.application.ports.input;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.Client;

public interface ICreateClientUseCase {
    Client createClient(@Valid Client client);

}
