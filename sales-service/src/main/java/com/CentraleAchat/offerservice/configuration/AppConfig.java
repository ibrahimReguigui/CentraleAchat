package com.CentraleAchat.offerservice.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class AppConfig {

        @Bean
        public String stripeSecretKey() {
            return "sk_test_51MgxHaHNMTYvQeOXjXmRUREoUzUWoNnk74AuzzLfr90dbB1STFTx9zTN9tWWRkMCN3I8dmaFRjzRoyZHroIIBQOD00iZBvNtk5"; // remplacer par votre clé secrète Stripe
        }

    }

