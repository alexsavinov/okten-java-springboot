package okten.javaspringboot.services;

import lombok.AllArgsConstructor;
import okten.javaspringboot.dao.ClientDAO;
import okten.javaspringboot.models.Client;
import okten.javaspringboot.models.dto.ClientDTO;
import okten.javaspringboot.utils.ClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientDAO clientDAO;
    private ClientUtil clientUtil;

    public List<ClientDTO> findAll() {
        List<Client> clients = clientDAO.findAll();
        return clientUtil.clientToDTO(clients);
    }

    public List<ClientDTO> findByName(String name) {
        List<Client> clients = clientDAO.findByName(name);
        return clientUtil.clientToDTO(clients);
    }

    public List<ClientDTO> findBySurname(String surname) {
        List<Client> clients = clientDAO.findBySurname(surname);
        return clientUtil.clientToDTO(clients);
    }

    public ClientDTO findById(int id) {
        if (id > 0) {
            Client client = clientDAO.findById(id).get();
            return clientUtil.clientToDTO(client);
        } else {
            throw new RuntimeException("ID must be greater than 0!");
        }
    }

    public ClientDTO save(ClientDTO clientDTO) {
        if (!Objects.equals(clientDTO.getClientName(), clientDTO.getClientSurname())) {
            Client clientToSave = clientUtil.dtoToClient(clientDTO);
            Client client = clientDAO.save(clientToSave);
            return clientUtil.clientToDTO(client);
        } else {
            throw new RuntimeException("Name and surname must be different!");
        }
    }

    public void deleteById(int id) {
        if (id > 0) {
            clientDAO.deleteById(id);
        } else {
            throw new RuntimeException("ID must be greater than 0!");
        }
    }

    public void update(int id, ClientDTO clientDTO) {
        Client client = clientUtil.dtoToClient(clientDTO);
        clientDAO.updateNameAndSurnameAndEmailById(
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                id);
    }
}
