package org.grandmasfood.springcloud.clients.application.usecases;

import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.ICreateClientUseCase;

public class CreateClientUseCaseImlp implements ICreateClientUseCase {
    @Override
    public Clients createClient(ClientsRequestDTO clientDTO) {
        return null;
    }
}
