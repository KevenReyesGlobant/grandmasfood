package org.grandmasfood.springcloud.clients.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.model.dto.ClientsDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IClientServices {
    Clients createClient(@Valid ClientsDTO clientDTO);
    Page<Clients> readAllActiveClients(Pageable pageable);
    Optional<Clients> readCLientsActiveById(Long id);
}
