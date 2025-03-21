package org.grandmasfood.springcloud.clients.application.ports.output;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ClientPersistencePort {
    Client save(Client client);

    Optional<Client> findActiveByDocument(String document);

    Optional<Client> findActiveById(Long id);

    Client deleteByDocument(String document);

    List<Client> findClientByValue(String orderBy, String direction);

}
