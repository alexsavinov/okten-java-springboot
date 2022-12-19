package okten.javaspringboot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import okten.javaspringboot.models.dto.ClientDTO;
import okten.javaspringboot.models.views.Views;
import okten.javaspringboot.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    /* Get all Clients */
    @GetMapping("")
    @JsonView({Views.Client.class})
    public ResponseEntity<List<ClientDTO>> getClients() {
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    /* Get Client by id */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable int id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    /* Get Clients by Name */
    @GetMapping("/name/{name}")
    @JsonView({Views.Client.class})
    public ResponseEntity<List<ClientDTO>> getClientsByName(@PathVariable String name) {
        return new ResponseEntity<>(clientService.findByName(name), HttpStatus.OK);
    }

   /* Get Clients by Surname */
    @GetMapping("/surname/{surname}")
    @JsonView({Views.Client.class})
    public ResponseEntity<List<ClientDTO>> getClientsBySurname(@PathVariable String surname) {
        return new ResponseEntity<>(clientService.findBySurname(surname), HttpStatus.OK);
    }

    /* Add new Client */
    @PostMapping("")
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.save(clientDTO), HttpStatus.CREATED);
    }

    /* Update Client by id and data */
    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable int id, @RequestBody ClientDTO clientDTO) {
        clientService.update(id, clientDTO);
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.CREATED);
    }

    /* Delete Client by id */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteClientById(@PathVariable int id) {
        clientService.deleteById(id);
    }
}
