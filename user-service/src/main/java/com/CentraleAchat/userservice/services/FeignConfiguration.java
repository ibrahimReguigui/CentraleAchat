package com.CentraleAchat.userservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FeignConfig {

    @Autowired
    private KeycloakClientRequestFactory keycloakClientRequestFactory;

    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new JacksonDecoder());
    }

    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new BearerTokenRequestInterceptor(keycloakClientRequestFactory);
    }
}
