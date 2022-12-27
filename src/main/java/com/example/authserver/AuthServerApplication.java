package com.example.authserver;

import com.example.authserver.jpa.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.UUID;

@SpringBootApplication
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> aapplicationListener(RegisteredClientRepository repository,
																	UserRepository userRepository,
																	PasswordEncoder encoder) {
		return new ApplicationListener<ApplicationReadyEvent>() {
			@Override
			public void onApplicationEvent(ApplicationReadyEvent event) {

				var user = new User();
				user.setUsername("frank");
				user.setPassword(encoder.encode("password"));
				user.addGroup(new Group("user"));

				user.setEnabled(true);

				userRepository.save(user);

				RegisteredClient registeredClient1 = RegisteredClient.withId(UUID.randomUUID().toString())
						.clientId("login-client")
						.clientSecret("secret")
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
						.tokenSettings(TokenSettings.builder()
								.reuseRefreshTokens(true)
								.build())
						.build();

				repository.save(registeredClient1);
			}
		};

	}

}
