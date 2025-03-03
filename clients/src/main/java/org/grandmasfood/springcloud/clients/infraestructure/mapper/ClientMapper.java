package org.grandmasfood.springcloud.clients.infraestructure.mapper;

import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.Clients;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    ClientsRequestDTO ClientsToClientsRequestDto(Clients clients);

//    @Mapping(target = "dataCreate", ignore = true)
    Clients ClientsRequestDtoToClients(ClientsRequestDTO clientsRequestDTO);
}
