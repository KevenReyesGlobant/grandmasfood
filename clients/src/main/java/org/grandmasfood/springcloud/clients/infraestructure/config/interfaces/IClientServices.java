package org.grandmasfood.springcloud.clients.infraestructure.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities.ClientsEntity;


import java.util.Optional;

public interface IClientServices {
    ClientsEntity createClient(@Valid ClientsRequestDTO clientDTO);

    ClientsEntity updateClient(@Valid ClientsRequestDTO clientDTO, String document);

    //    Page<Clients> readAllActiveClients(Pageable pageable);
    Optional<ClientsEntity> readCLientsActiveById(Long id);

    Optional<ClientsEntity> readActiveClientsByDocument(String document);

    Optional<ClientsEntity> deleteClientsByDocument(String document);
}
