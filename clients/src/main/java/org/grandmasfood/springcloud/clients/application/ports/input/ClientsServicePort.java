package org.grandmasfood.springcloud.clients.application.ports.input;

import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.List;


public interface ClientsServicePort {
    Client findById(Long id);

    List<Client> findAll();

    Client save(Client client);

    Client update(String document, Client client);

    Client findActiveByDocument(String document);

    Client findActiveById(Long id);

    Client deleteByDocument(String document);


}
