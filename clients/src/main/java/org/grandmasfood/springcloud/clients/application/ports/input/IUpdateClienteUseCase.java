package org.grandmasfood.springcloud.clients.application.ports.input;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.Optional;

public interface IUpdateClienteUseCase {
    Optional<Client> updateClient( @Valid Client client, String document);
}
