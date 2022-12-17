package com.example.authserver.mapper;

import com.example.authserver.jpa.Client;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


class ClientMapperTest {

    @Test
    void testClientToRegisteredClient() {

        var mapper = new ClientMapper();

        var client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setClientId("clientid");
        client.setClientSettings(Map.of("name","John","age",54));
        client.setTokenSettings(Map.of("name","John"));
        client.setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE.getValue()));
        client.setRedirectUris(Set.of("http://localhost:8080/login"));

        var registeredClient = mapper.clientToRegisteredClient(client);

        assertThat(registeredClient.getId()).isEqualTo(client.getId());
        assertThat(registeredClient.getClientId()).isEqualTo(client.getClientId());
        assertThat(registeredClient.getClientAuthenticationMethods().size()).isEqualTo(1);
        assertThat(registeredClient.getAuthorizationGrantTypes().size()).isEqualTo(1);
        assertThat(registeredClient.getClientIdIssuedAt()).isEqualTo(client.getClientIdIssuedAt());
        assertThat(registeredClient.getClientSecretExpiresAt()).isEqualTo(client.getClientSecretExpiresAt());

    }

}