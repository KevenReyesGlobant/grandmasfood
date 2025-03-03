package org.grandmasfood.springcloud.clients.domain.ports.in;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;

import java.util.Optional;

public interface UpdateClienteUseCase {
    Optional<Clients> updateClientUseCase(@Valid ClientsRequestDTO clientDTO, String document);
}
