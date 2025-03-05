package org.grandmasfood.springcloud.clients.application.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.domain.ports.in.*;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientsService implements ICreateClientUseCase, IRetrieveClientUseCase, IDeleteClientUsesCase, IUpdateClienteUseCase {

    private final ICreateClientUseCase iCreateClientUseCase;
    private final IRetrieveClientUseCase iRetrieveClientUseCase;
    private final IDeleteClientUsesCase iDeleteClientUsesCase;
    private final IUpdateClienteUseCase iUpdateClienteUseCase;
    private final IGetAditionalClientInfoUseCase iGetAditionalClientInfoUseCase;


    public ClientsService(ICreateClientUseCase iCreateClientUseCase, IRetrieveClientUseCase iRetrieveClientUseCase, IDeleteClientUsesCase iDeleteClientUsesCase, IUpdateClienteUseCase iUpdateClienteUseCase, IGetAditionalClientInfoUseCase iGetAditionalClientInfoUseCase) {
        this.iCreateClientUseCase = iCreateClientUseCase;
        this.iRetrieveClientUseCase = iRetrieveClientUseCase;
        this.iDeleteClientUsesCase = iDeleteClientUsesCase;
        this.iUpdateClienteUseCase = iUpdateClienteUseCase;
        this.iGetAditionalClientInfoUseCase = iGetAditionalClientInfoUseCase;
    }


    @Override
    public Client createClient(Client client) {
        return iCreateClientUseCase.createClient(client);
    }

    @Override
    public Optional<Client> deleteClientsByDocument(String document) {
        return iDeleteClientUsesCase.deleteClientsByDocument(document);
    }

    @Override
    public Optional<Client> readCLientsActiveById(Long id) {
        return iRetrieveClientUseCase.readCLientsActiveById(id);
    }

    @Override
    public Optional<Client> readActiveClientsByDocument(String document) {
        return iRetrieveClientUseCase.readActiveClientsByDocument(document);
    }

    @Override
    public Optional<Client> updateClient(Client client, String document) {
        return iUpdateClienteUseCase.updateClient(client, document);
    }
}
