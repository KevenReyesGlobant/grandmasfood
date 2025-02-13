package config.interfaces;

import model.dto.ClientDTO;
import model.entity.Client;

public interface IClientsService {
    Client createClient(ClientDTO clientDTO);
}
