package org.grandmasfood.springcloud.clients.application.usecases;

import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.IRetrieveClientUseCase;

import java.util.Optional;

public class RetrieveClientUsesCaseImlp implements IRetrieveClientUseCase {
    @Override
    public Optional<Clients> retrieveCLientsActiveByIdUseCase(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Clients> retrieveActiveClientsByDocument(String document) {
        return Optional.empty();
    }
}
