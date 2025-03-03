package org.grandmasfood.springcloud.clients.domain.ports.out;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.Clients;

import java.util.Optional;

public interface ClientRepositoryPort {
    Clients createClient(@Valid ClientsRequestDTO clientDTO);

    Clients updateClient(@Valid ClientsRequestDTO clientDTO, String document);

    Optional<Clients> readCLientsActiveById(Long id);

    Optional<Clients> readActiveClientsByDocument(String document);

    Optional<Clients> deleteClientsByDocument(String document);
}
