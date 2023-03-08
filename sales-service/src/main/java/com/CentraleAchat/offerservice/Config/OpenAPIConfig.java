package com.CentraleAchat.offerservice.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class OpenAPIConfig {


    @Configuration
    public class SpringDocConfig{
        @Bean
        public OpenAPI springShopOpenAPI() {
            return new OpenAPI().info(infoAPI());

        }

        private Info infoAPI() {
            return new Info().title("SpringDoc-Demo")
                    .description("TP étude de cas")
                    .contact(contactAPI());
        }

        private Contact contactAPI() {

            Contact contact = new Contact().name("Equipe ASI II")
                    .email("*************@esprit.tn")
                    .url("https://www.linkedin.com/in/**********/");
            return contact;
        }

    }
}
