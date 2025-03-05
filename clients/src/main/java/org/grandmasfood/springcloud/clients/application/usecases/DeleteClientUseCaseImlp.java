package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.application.ports.input.IDeleteClientUsesCase;

import java.util.Optional;

public class DeleteClientUseCaseImlp implements IDeleteClientUsesCase {

    private final ClientPersistencePort clientPersistencePort;

    public DeleteClientUseCaseImlp(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public Optional<Client> deleteClientsByDocument(String document) {
        return clientPersistencePort.deleteClientsByDocument(document);
    }
}
