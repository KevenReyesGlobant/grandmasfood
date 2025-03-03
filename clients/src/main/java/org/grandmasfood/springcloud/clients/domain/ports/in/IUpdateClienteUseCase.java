package org.grandmasfood.springcloud.clients.domain.ports.in;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.Clients;

public interface IUpdateClienteUseCase {
    Clients updateClient(@Valid ClientsRequestDTO clientDTO, String document);
}
