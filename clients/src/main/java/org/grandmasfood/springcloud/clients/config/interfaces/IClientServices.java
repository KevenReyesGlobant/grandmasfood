package org.grandmasfood.springcloud.clients.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.model.dto.ClientsDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;

public interface IClientServices {
    Clients createClient(@Valid ClientsDTO clientDTO);
}
