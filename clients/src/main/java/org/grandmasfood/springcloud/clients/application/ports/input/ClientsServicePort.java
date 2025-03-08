package org.grandmasfood.springcloud.clients.application.ports.input;

import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientsServicePort {
    Client findById(Long id);

    List<Client> findAll();

    Client save(Client client);

    Client update(Long id, Client client);

    void deleteById(Long id);

    Client findActiveByDocument(String document);

    Client findActiveById(Long id);


}
