package com.example.authserver.jpa;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "`client`")
public class Client {

    public Client() {
    }

    @Id
    private String id;
    @Column(length=50)
    @NaturalId
    private String clientId;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String,Object> clientSettings;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Set<String> clientAuthenticationMethods;
    private Instant clientIdIssuedAt = Instant.now();
    private String clientSecret;
    private Instant clientSecretExpiresAt = Instant.now().plus(30, ChronoUnit.DAYS);
    private String clientName;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Set<String> authorizationGrantTypes;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Set<String> redirectUris;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Set<String> scopes;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String,Object> tokenSettings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Map<String, Object> getClientSettings() {
        return clientSettings;
    }

    public void setClientSettings(Map<String, Object> clientSettings) {
        this.clientSettings = clientSettings;
    }

    public Set<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }
    public void setClientAuthenticationMethods(Set<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public Instant getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public void setClientIdIssuedAt(Instant clientIdIssuedAt) {
        if(clientIdIssuedAt != null)
            this.clientIdIssuedAt = clientIdIssuedAt;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Instant getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
        if(clientSecretExpiresAt != null)
            this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(Set<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public Map<String, Object> getTokenSettings() {
        return tokenSettings;
    }

    public void setTokenSettings(Map<String, Object> tokenSettings) {
        this.tokenSettings = tokenSettings;
    }
}
