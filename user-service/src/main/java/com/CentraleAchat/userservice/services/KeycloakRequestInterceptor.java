package com.CentraleAchat.userservice.services;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class KeycloakRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        KeycloakSecurityContext context = getKeycloakSecurityContext();
        if (context != null) {
            template.header("Authorization", "Bearer " + context.getTokenString());
        }
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (token != null && token.getAccount() != null) {
            return token.getAccount().getKeycloakSecurityContext();
        }
        return null;
    }
}

