package org.grandmasfood.springcloud.clients.application.usecases;

import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.IUpdateClienteUseCase;

import java.util.Optional;

public class UpdateClientUseCaseImlp implements IUpdateClienteUseCase {
    @Override
    public Optional<Clients> updateClientUseCase(ClientsRequestDTO clientDTO, String document) {
        return Optional.empty();
    }
}
