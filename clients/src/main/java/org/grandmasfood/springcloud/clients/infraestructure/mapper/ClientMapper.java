package org.grandmasfood.springcloud.clients.infraestructure.mapper;

import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    ClientsRequestDTO ClientsToClientsRequestDto(ClientsEntity clientsEntity);

//    @Mapping(target = "dataCreate", ignore = true)
    ClientsEntity ClientsRequestDtoToClients(ClientsRequestDTO clientsRequestDTO);
}
