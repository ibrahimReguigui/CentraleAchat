package com.CentraleAchat.userservice.controllers;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.Token;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class KeycloakConfig {

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8099/")
                .realm("pidev")
                .clientId("pidev")
                .clientSecret("pvs3oudngIxtojOSiITvfcU2QTn7cgkr")
                .username("systemadmin")
                .password("systemadmin")
                .build();
    }
    public  AccessToken getAccessToken() {
        KeycloakSecurityContext context = (KeycloakSecurityContext) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return context.getToken();
    }
    public AccessToken whoAmI() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        AccessToken accessToken = authentication.getAccount().getKeycloakSecurityContext().getToken();
        return accessToken;
    }
}

