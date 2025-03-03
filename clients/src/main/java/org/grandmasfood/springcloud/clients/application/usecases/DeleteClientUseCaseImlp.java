package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.IDeleteClientUsesCase;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

import java.util.Optional;

public class DeleteClientUseCaseImlp implements IDeleteClientUsesCase {

    private final ClientsReposity clientsReposity;

    public DeleteClientUseCaseImlp(ClientsReposity clientsReposity) {
        this.clientsReposity = clientsReposity;
    }


    @Override
    public Optional<Clients> deleteClientsByDocument(String document) {
        Clients clients = clientsReposity.findClientsActiveByDocument(document);
        if (clients != null) {
            clients.setActive(false);
            clientsReposity.save(clients);
            return Optional.of(clients);
        }
        return Optional.empty();

    }


}
