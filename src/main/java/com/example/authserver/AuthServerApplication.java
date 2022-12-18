package com.example.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootApplication
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> aapplicationListener(RegisteredClientRepository repository) {

		return new ApplicationListener<ApplicationReadyEvent>() {
			@Override
			public void onApplicationEvent(ApplicationReadyEvent event) {

				RegisteredClient registeredClient1 = RegisteredClient.withId(UUID.randomUUID().toString())
						.clientId("login-client")
						.clientSecret("{noop}secret")
						.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
						.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
						.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
						.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
						.redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
						.redirectUri("http://127.0.0.1:8080/authorized")
						.scope(OidcScopes.OPENID)
						.scope(OidcScopes.PROFILE)
						.scope("message.read")
						.scope("message.write")
						.clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
						.build();

				repository.save(registeredClient1);
			}
		};

	}

}
