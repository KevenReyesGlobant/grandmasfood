package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.mapper;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.request.ClientsCreateRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRestMapper {
    Client toClient(ClientsCreateRequestDTO clientsCreateRequestDTO);

}
