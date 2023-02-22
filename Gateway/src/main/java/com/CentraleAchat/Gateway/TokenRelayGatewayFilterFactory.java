package com.CentraleAchat.Gateway;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class TokenRelayGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenRelayGatewayFilterFactory.Config> {

    public TokenRelayGatewayFilterFactory() {
        super(Config.class);
    }

    public static class Config {
        // No configuration properties needed for this filter
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication instanceof KeycloakAuthenticationToken) {
                String token = ((KeycloakAuthenticationToken) authentication).getAccount().getKeycloakSecurityContext().getTokenString();
                ServerHttpRequest request = exchange.getRequest().mutate().header("Authorization", "Bearer " + token).build();
                return chain.filter(exchange.mutate().request(request).build());
            }
            return chain.filter(exchange);
        };
    }
}

