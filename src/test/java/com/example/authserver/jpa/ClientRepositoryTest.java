package com.example.authserver.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testPersistence() {

        var expectedId = UUID.randomUUID().toString();
        Client client = new Client();
        client.setId(expectedId);
        client.setClientId("order-service");

        Map<String,Object> settings = Map.of("name", "Frank", "city", "Leesburg");
        client.setClientSettings(settings);
        client.setClientAuthenticationMethods(Set.of("Justin","Colin","Ethan"));
        clientRepository.save(client);

        var found = clientRepository.findByClientId("order-service").get();
        System.out.println( found.getClientSettings() );
        System.out.println( found.getClientAuthenticationMethods() );

    }


}