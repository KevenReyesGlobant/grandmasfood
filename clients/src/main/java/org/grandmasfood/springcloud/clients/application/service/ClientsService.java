package org.grandmasfood.springcloud.clients.application.service;

import org.grandmasfood.springcloud.clients.application.ports.input.*;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.domain.exceptions.ClientNotFoundException;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClientsService implements ICreateClientUseCase, IRetrieveClientUseCase, IDeleteClientUsesCase, IUpdateClienteUseCase {

    private final ClientPersistencePort clientPersistencePort;

    public ClientsService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }


    @Override
    public Client createClient(Client client) {
        return clientPersistencePort.createClient(client);
    }

    @Override
    public Optional<Client> deleteClientsByDocument(String document) {
        if (clientPersistencePort.readActiveClientsByDocument(document).isEmpty()) {
            throw new ClientNotFoundException();

        }
        return clientPersistencePort.deleteClientsByDocument(document);
    }

    @Override
    public Optional<Client> readCLientsActiveById(Long id) {
        return Optional.ofNullable(clientPersistencePort.readCLientsActiveById(id).orElseThrow(ClientNotFoundException::new));
    }

    @Override
    public Optional<Client> readActiveClientsByDocument(String document) {
        return clientPersistencePort.readActiveClientsByDocument(document);
    }

    @Override
    public Optional<Client> updateClient(Client client, String document) {
        return Optional.ofNullable(clientPersistencePort.deleteClientsByDocument(document).map(saveClient -> {
            saveClient.setName(client.getName());
            saveClient.setDocument(client.getDocument());
            return clientPersistencePort.createClient(saveClient);
        }).orElseThrow(ClientNotFoundException::new));
    }
}