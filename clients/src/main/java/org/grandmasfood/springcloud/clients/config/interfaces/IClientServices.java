package org.grandmasfood.springcloud.clients.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;


import java.util.Optional;

public interface IClientServices {
    Clients createClient(@Valid ClientsRequestDTO clientDTO);

    Clients updateClient(@Valid ClientsRequestDTO clientDTO, String document);

    //    Page<Clients> readAllActiveClients(Pageable pageable);
    Optional<Clients> readCLientsActiveById(Long id);

    Optional<Clients> readActiveClientsByDocument(String document);

    Optional<Clients> deleteClientsByDocument(String document);
}
