package com.example.authserver.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class AuthorizationConsentRepositoryTest {

    @Autowired
    AuthorizationConsentRepository authorizationConsentRepository;

    @Test
    void testFindByRegisteredClientIdAndPrincipalName() {

        var authorizationConsent = new AuthorizationConsent();
        authorizationConsent.setPrincipalName("john");
        authorizationConsent.setAuthorities(Set.of("authority1","authority2"));
        authorizationConsent.setRegisteredClientId("test-service");
        authorizationConsentRepository.save(authorizationConsent);

        var found = authorizationConsentRepository
                .findByRegisteredClientIdAndPrincipalName("test-service", "john").get();

        assertThat(found).isNotNull();
        assertThat(found.getAuthorities().size()).isEqualTo(authorizationConsent.getAuthorities().size());
        assertThat(found.getAuthorities()).isEqualTo(authorizationConsent.getAuthorities());

    }
}