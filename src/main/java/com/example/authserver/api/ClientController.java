package com.example.authserver.api;

import com.example.authserver.jpa.Client;
import com.example.authserver.jpa.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping
    public List<Client> clients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client findClient(@PathVariable UUID id) {
        var found = clientRepository.findById(id.toString());

        if(found.isPresent()) {
           return found.get();
        }
        throw new UserNotFoundException(id.toString());
    }

    @PostMapping("/")
    public ResponseEntity<Object> createClient(Client client) {
        var newClient = clientRepository.save(client);
        var location = UriComponentsBuilder.fromPath("/api/users/{username}")
                .buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
