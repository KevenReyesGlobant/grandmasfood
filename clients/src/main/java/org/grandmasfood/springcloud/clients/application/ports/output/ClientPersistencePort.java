package org.grandmasfood.springcloud.clients.application.ports.output;

import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientPersistencePort {
    Optional<Client> findById(Long id);

    List<Client> findAll();

    Client save(Client client);

    void deleteById(Long id);

    Optional<Client> findActiveByDocument(String document);

    Optional<Client> findActiveById(Long id);

}
