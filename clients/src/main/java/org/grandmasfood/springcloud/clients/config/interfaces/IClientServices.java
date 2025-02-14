package org.grandmasfood.springcloud.clients.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.model.dto.ClientsDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientServices {
    Clients createClient(@Valid ClientsDTO clientDTO);
    Page<Clients> getAllClients(Pageable pageable);
}
