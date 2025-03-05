package org.grandmasfood.springcloud.clients.application.usecases;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.application.ports.input.ICreateClientUseCase;

public class CreateClientUseCaseImlp implements ICreateClientUseCase {

    private final ClientPersistencePort clientPersistencePort;


    public CreateClientUseCaseImlp(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }


    @Override
    public Client createClient(Client client) {
        return clientPersistencePort.createClient(client);
    }
}
