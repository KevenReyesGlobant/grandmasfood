package service;

import config.interfaces.IClientsService;
import jakarta.validation.Valid;
import model.dto.ClientDTO;
import model.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;

@Service
public class ClientsService implements IClientsService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public Client createClient(@Valid ClientDTO clientDTO) {
        Client client = new Client();
        client.setUuid(clientDTO.uuid());
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setDocument(clientDTO.document());
        client.setPhone(clientDTO.phone());
        client.setDeliveryAddress(clientDTO.deliveryAddress());
        return clientRepository.save(client);
    }
}
