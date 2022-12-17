package com.example.authserver.jpa;

import com.example.authserver.mapper.ClientMapper;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JpaRegisteredClientRepository implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper = new ClientMapper();

    public JpaRegisteredClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

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
