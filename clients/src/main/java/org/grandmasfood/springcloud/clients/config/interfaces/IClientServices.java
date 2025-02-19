package org.grandmasfood.springcloud.clients.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.model.dto.ClientsDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;


import java.util.Optional;

public interface IClientServices {
    Clients createClient(@Valid ClientsDTO clientDTO);

    //    Page<Clients> readAllActiveClients(Pageable pageable);
    Optional<Clients> readCLientsActiveById(Long id);

    Optional<Clients> readActiveClientsByDocument(String document);

    Optional<Clients> deleteClientsByDocument(String document);
}
