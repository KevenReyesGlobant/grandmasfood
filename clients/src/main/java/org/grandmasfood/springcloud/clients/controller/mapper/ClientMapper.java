package org.grandmasfood.springcloud.clients.controller.mapper;

import org.grandmasfood.springcloud.clients.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    ClientsRequestDTO ClientsToClientsRequestDto(Clients clients);

//    @Mapping(target = "dataCreate", ignore = true)
    Clients ClientsRequestDtoToClients(ClientsRequestDTO clientsRequestDTO);
}
