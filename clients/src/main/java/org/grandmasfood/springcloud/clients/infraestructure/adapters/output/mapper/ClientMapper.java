package org.grandmasfood.springcloud.clients.infraestructure.adapters.output.mapper;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities.ClientsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientsEntity toClientEntity(Client client);

    Client toClient(ClientsEntity clientsEntity);

    List<Client> toClientsList(List<ClientsEntity> clientsEntities);

}
