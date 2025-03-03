package org.grandmasfood.springcloud.clients.application.usecases;

import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.IDeleteClientUsesCase;

import java.util.Optional;

public class DeleteClientUseCaseImlp implements IDeleteClientUsesCase {

    @Override
    public Optional<Clients> deleteClientsByDocument(String document) {
        return Optional.empty();
    }
}
