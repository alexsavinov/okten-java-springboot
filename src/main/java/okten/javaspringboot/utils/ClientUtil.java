package okten.javaspringboot.utils;

import lombok.AllArgsConstructor;
import okten.javaspringboot.models.Client;
import okten.javaspringboot.models.dto.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClientUtil {

    public List<ClientDTO> clientToDTO(List<Client> clients) {
        return clients.stream()
                .map(client -> new ClientDTO(client.getId(), client.getName(), client.getSurname(), client.getEmail()))
                .collect(Collectors.toList());
    }

    public ClientDTO clientToDTO(Client client) {
        return new ClientDTO(client.getId(), client.getName(), client.getSurname(), client.getEmail());
    }

    public Client dtoToClient(ClientDTO clientDTO) {
        return Client.builder()
                .name(clientDTO.getClientName())
                .surname(clientDTO.getClientSurname())
                .email(clientDTO.getClientEmail())
                .build();
    }
}
