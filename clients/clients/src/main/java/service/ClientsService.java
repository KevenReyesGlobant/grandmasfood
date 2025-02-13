package service;

import jakarta.validation.Valid;
import model.dto.ClientDTO;
import model.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;

@Service
public class ClientsService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client createClient(@Valid ClientDTO clientDTO) {
        return null;

    }
}
