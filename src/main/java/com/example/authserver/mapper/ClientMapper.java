package com.example.authserver.mapper;

import com.example.authserver.jpa.Client;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClientMapper {

    public Client fromRegisteredClient(RegisteredClient registeredClient) {
        Client client = new Client();

        client.setId( registeredClient.getId() );
        client.setClientId(registeredClient.getClientId());
        client.setClientSettings( registeredClient.getClientSettings().getSettings() );

        client.setClientAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods().stream()
                        .map(ClientAuthenticationMethod::getValue)
                        .collect(Collectors.toSet()));

        client.setClientName(registeredClient.getClientName());
        client.setClientSecret( registeredClient.getClientSecret());
        client.setClientIdIssuedAt( registeredClient.getClientIdIssuedAt() );

        client.setAuthorizationGrantTypes( registeredClient.getAuthorizationGrantTypes().stream()
                .map(AuthorizationGrantType::getValue)
                .collect(Collectors.toSet()));

        client.setClientSecretExpiresAt( registeredClient.getClientSecretExpiresAt() );
        client.setClientSettings( registeredClient.getClientSettings().getSettings() );
        client.setRedirectUris( registeredClient.getRedirectUris() );
        client.setScopes( registeredClient.getScopes() );

        return client;
    }

    public RegisteredClient clientToRegisteredClient(Client client) {

        return RegisteredClient.withId(client.getId())
                .clientId(client.getClientId())
                .clientIdIssuedAt(client.getClientIdIssuedAt())
                .clientName(client.getClientName())
                .clientSecret(client.getClientSecret())
                .clientSettings(clientSettingsFromMap( client.getClientSettings()))
                .authorizationGrantTypes( authorizationGrantTypeFromSet(client.getAuthorizationGrantTypes()))
                .clientAuthenticationMethods( clientAuthenticationMethodFromSet(client.getClientAuthenticationMethods()))
                .clientSecretExpiresAt(client.getClientSecretExpiresAt())
                .redirectUris(redirectUrisFromSet(client.getRedirectUris()))
                .scopes(scopesFromSet(client.getScopes()))
                .tokenSettings(tokenSettingsFromMap(client.getTokenSettings()))
                .build();

    }

    public Consumer<Set<String>> scopesFromSet( Set<String> values ) {
        return new Consumer<Set<String>>() {
            @Override
            public void accept(Set<String> scopes) {
                if(values != null) {
                    scopes.addAll(values);
                }
            }
        };
    }

    public Consumer<Set<String>> redirectUrisFromSet( Set<String> values ) {
        return new Consumer<Set<String>>() {
            @Override
            public void accept(Set<String> redirectUris) {
                if(values != null) {
                    redirectUris.addAll(values);
                }
            }
        };
    }

    public Consumer<Set<ClientAuthenticationMethod>> clientAuthenticationMethodFromSet( Set<String> values ) {
        return new Consumer<Set<ClientAuthenticationMethod>>() {
            @Override
            public void accept(Set<ClientAuthenticationMethod> clientAuthenticationMethods) {
                if(values != null) {
                    values.stream().forEach(value ->{
                        clientAuthenticationMethods.add( new ClientAuthenticationMethod(value));
                    });
                }
            }
        };
    }

    public Consumer<Set<AuthorizationGrantType>> authorizationGrantTypeFromSet(Set<String> values ) {
        return new Consumer<Set<AuthorizationGrantType>>() {
            @Override
            public void accept(Set<AuthorizationGrantType> authorizationGrantTypes) {
                if(values != null) {
                    values.stream().forEach(value ->{
                        authorizationGrantTypes.add( new AuthorizationGrantType(value));
                    });
                }
            }
        };
    }

    public TokenSettings tokenSettingsFromMap(Map<String,Object> tokenSettings) {
        if(tokenSettings != null) {
            return TokenSettings.withSettings(tokenSettings).build();
        }
        else {
            return TokenSettings.withSettings(new HashMap<>()).build();
        }
    }

    public ClientSettings clientSettingsFromMap(Map<String,Object> clientSettings) {
        if(clientSettings != null) {
            return ClientSettings.withSettings(clientSettings).build();
        }
        else {
            return ClientSettings.withSettings(new HashMap<>()).build();
        }
    }

}
