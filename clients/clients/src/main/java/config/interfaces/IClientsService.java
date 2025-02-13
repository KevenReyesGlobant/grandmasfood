package config.interfaces;

import jakarta.validation.Valid;
import model.dto.ClientDTO;
import model.entity.Client;

public interface IClientsService {
    Client createClient(@Valid ClientDTO clientDTO);
}
