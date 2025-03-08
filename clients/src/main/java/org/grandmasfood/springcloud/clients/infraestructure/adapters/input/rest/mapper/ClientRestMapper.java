package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.mapper;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.request.ClientsCreateRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response.ClientsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientRestMapper {
    Client toClient(ClientsCreateRequestDTO clientsCreateRequestDTO);

    ClientsResponseDTO toClientsResponseDTO(Client client);


    List<ClientsResponseDTO> toClientResponseList(List<Client> clientList);


}
