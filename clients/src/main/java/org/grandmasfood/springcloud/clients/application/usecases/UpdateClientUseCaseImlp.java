package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.application.ports.input.IUpdateClienteUseCase;

import java.util.Optional;

public class UpdateClientUseCaseImlp implements IUpdateClienteUseCase {
    private final ClientPersistencePort clientPersistencePort;

    public UpdateClientUseCaseImlp(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }


    @Override
    public Optional<Client> updateClient(Client client, String document) {

        return clientPersistencePort.updateClient(client, document);
    }
}
