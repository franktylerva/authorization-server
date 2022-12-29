export type Client = {
    id: string;
    clientId: string;
    clientSecret: string;
    clientName: string;

    clientSecretExpiresAt: Date;
    authorizationGrantTypes: string[];
    clientAuthenticationMethods: string[];
    clientSettings: ClientSettings;
}

export type ClientSettings = {
    "settings.client.require-authorization-consent": boolean;
    "settings.client.require-proof-key": boolean;
}