package org.grandmasfood.springcloud.clients.application.service;

import org.grandmasfood.springcloud.clients.application.ports.input.*;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.domain.exceptions.ClientNotFoundException;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClientService implements ClientsServicePort {

    private final ClientPersistencePort persistencePort;

    public ClientService(ClientPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Client save(Client client) {
        return persistencePort.save(client);
    }

    @Override
    public Client findActiveByDocument(String document) {
        return persistencePort.findActiveByDocument(document)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client findActiveById(Long id) {
        return persistencePort.findActiveById(id)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Optional<Client> deleteByDocument(String document) {
        return Optional.ofNullable(persistencePort.deleteByDocument(document));
    }


    @Override
    public Client findById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public List<Client> findAll() {
        return persistencePort.findAll();
    }


    @Override
    public Client update(Long id, Client client) {
        return null;
    }


}



