package org.grandmasfood.springcloud.clients.domain.ports.in;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;

public interface CreateClientUseCase {
    Clients createClientUseCase(@Valid ClientsRequestDTO clientDTO);

}
