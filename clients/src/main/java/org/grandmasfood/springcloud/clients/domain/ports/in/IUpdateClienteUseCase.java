package org.grandmasfood.springcloud.clients.domain.ports.in;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;

import java.util.Optional;

public interface IUpdateClienteUseCase {
    Optional<Client> updateClient( @Valid Client client, String document);
}
