package org.grandmasfood.springcloud.clients.application.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.ports.in.ICreateClientUseCase;
import org.grandmasfood.springcloud.clients.domain.ports.in.IDeleteClientUsesCase;
import org.grandmasfood.springcloud.clients.domain.ports.in.IRetrieveClientUseCase;
import org.grandmasfood.springcloud.clients.domain.ports.in.IUpdateClienteUseCase;
import org.grandmasfood.springcloud.clients.domain.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientsService implements ICreateClientUseCase, IRetrieveClientUseCase, IDeleteClientUsesCase, IUpdateClienteUseCase {

    private final ICreateClientUseCase iCreateClientUseCase;
    private final IRetrieveClientUseCase iRetrieveClientUseCase;
    private final IDeleteClientUsesCase iDeleteClientUsesCase;
    private final IUpdateClienteUseCase iUpdateClienteUseCase;


    public ClientsService(ICreateClientUseCase iCreateClientUseCase, IRetrieveClientUseCase iRetrieveClientUseCase, IDeleteClientUsesCase iDeleteClientUsesCase, IUpdateClienteUseCase iUpdateClienteUseCase) {
        this.iCreateClientUseCase = iCreateClientUseCase;
        this.iRetrieveClientUseCase = iRetrieveClientUseCase;
        this.iDeleteClientUsesCase = iDeleteClientUsesCase;
        this.iUpdateClienteUseCase = iUpdateClienteUseCase;
    }

    @Transactional
    @Override
    public Clients createClient(@Valid ClientsRequestDTO clientDTO) {
        return iCreateClientUseCase.createClient(clientDTO);
    }


    @Override
    @Transactional
    public Clients updateClient(@Valid ClientsRequestDTO clientDTO, @Valid String document) {

        return iUpdateClienteUseCase.updateClient(clientDTO, document);
    }

    @Transactional
    @Override
    public Optional<Clients> readCLientsActiveById(@Valid Long id) {
        return iRetrieveClientUseCase.readCLientsActiveById(id);
    }

    @Transactional
    @Override
    public Optional<Clients> readActiveClientsByDocument(@Valid String document) {
        return iRetrieveClientUseCase.readActiveClientsByDocument(document);
    }

    @Override
    public Optional<Clients> deleteClientsByDocument(String document) {
        return iDeleteClientUsesCase.deleteClientsByDocument(document);
    }


}
