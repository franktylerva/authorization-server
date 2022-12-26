package com.example.authserver.service;

import com.example.authserver.jpa.ClientRepository;
import com.example.authserver.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaRegisteredClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper = new ClientMapper();

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(clientMapper.fromRegisteredClient(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {

        var found = clientRepository.findById(id);
        if(found.isPresent())
            return clientMapper.clientToRegisteredClient(found.get());

        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var found = clientRepository.findByClientId(clientId);
        if(found.isPresent())
            return clientMapper.clientToRegisteredClient(found.get());

        return null;
    }
}
