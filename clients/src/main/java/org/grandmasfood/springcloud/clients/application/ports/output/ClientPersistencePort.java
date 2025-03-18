package org.grandmasfood.springcloud.clients.application.ports.output;

import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.Optional;

public interface ClientPersistencePort {
    Client save(Client client);

    Optional<Client> findActiveByDocument(String document);

    Optional<Client> findActiveById(Long id);

    Client deleteByDocument(String document);
}
