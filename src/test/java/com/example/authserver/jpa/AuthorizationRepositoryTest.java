package com.example.authserver.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest
class AuthorizationRepositoryTest {

    @Autowired
    AuthorizationRepository authorizationRepository;

    @Test
    void testFindByAuthorizationCodeValue() {

        Authorization authorization = new Authorization();
        authorization.setId(UUID.randomUUID().toString() );
        authorization.setAuthorizedScopes(Set.of("scope1","scope2","scope3"));
        authorization.setAuthorizationCodeValue("zzzzz");
        authorizationRepository.save(authorization);

        var found = authorizationRepository.findByAuthorizationCodeValue("zzzzz").get();
        assertThat(found).isNotNull();
        assertThat(found.getAuthorizedScopes().size()).isEqualTo(authorization.getAuthorizedScopes().size());
        assertThat(found.getAuthorizedScopes()).isEqualTo(authorization.getAuthorizedScopes());

    }

    @Test
    void testFindByAccessTokenValue() {

        Authorization authorization = new Authorization();
        authorization.setId(UUID.randomUUID().toString() );
        authorization.setAccessTokenValue("zzzzz");
        authorization.setAuthorizedScopes(Set.of("scope1","scope2","scope3"));
        authorization.setAuthorizationCodeValue("zzzzz");
        authorizationRepository.save(authorization);

        var found = authorizationRepository.findByAccessTokenValue("zzzzz").get();
        assertThat(found).isNotNull();
        assertThat(found.getAuthorizedScopes().size()).isEqualTo(authorization.getAuthorizedScopes().size());
        assertThat(found.getAuthorizedScopes()).isEqualTo(authorization.getAuthorizedScopes());

    }

    @Test
    void testFindByRefreshTokenValue() {

        Authorization authorization = new Authorization();
        authorization.setId(UUID.randomUUID().toString() );
        authorization.setAccessTokenValue("zzzzz");
        authorization.setRefreshTokenValue("aaaaa");
        authorization.setAuthorizedScopes(Set.of("scope1","scope2","scope3"));
        authorization.setAuthorizationCodeValue("zzzzz");
        authorizationRepository.save(authorization);

        var found = authorizationRepository.findByRefreshTokenValue("aaaaa").get();
        assertThat(found).isNotNull();
        assertThat(found.getAuthorizedScopes().size()).isEqualTo(authorization.getAuthorizedScopes().size());
        assertThat(found.getAuthorizedScopes()).isEqualTo(authorization.getAuthorizedScopes());

    }

    @Test
    void testFindByState() {

        Authorization authorization = new Authorization();
        authorization.setId(UUID.randomUUID().toString() );
        authorization.setAccessTokenValue("zzzzz");
        authorization.setRefreshTokenValue("aaaaa");
        authorization.setState("state");
        authorization.setAuthorizedScopes(Set.of("scope1","scope2","scope3"));
        authorization.setAuthorizationCodeValue("zzzzz");
        authorizationRepository.save(authorization);

        var found = authorizationRepository.findByState("state").get();
        assertThat(found).isNotNull();
        assertThat(found.getAuthorizedScopes().size()).isEqualTo(authorization.getAuthorizedScopes().size());
        assertThat(found.getAuthorizedScopes()).isEqualTo(authorization.getAuthorizedScopes());

    }

    @Test
    void testFindByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue() {

        Authorization authorization = new Authorization();
        authorization.setId(UUID.randomUUID().toString() );
        authorization.setAccessTokenValue("zzzzz");
        authorization.setRefreshTokenValue("aaaaa");
        authorization.setState("state");
        authorization.setAuthorizedScopes(Set.of("scope1","scope2","scope3"));
        authorization.setAuthorizationCodeValue("zzzzz");
        authorizationRepository.save(authorization);

        var found = authorizationRepository.findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValue("zzzzz").get();
        assertThat(found).isNotNull();
        assertThat(found.getAuthorizedScopes().size()).isEqualTo(authorization.getAuthorizedScopes().size());
        assertThat(found.getAuthorizedScopes()).isEqualTo(authorization.getAuthorizedScopes());

    }
}