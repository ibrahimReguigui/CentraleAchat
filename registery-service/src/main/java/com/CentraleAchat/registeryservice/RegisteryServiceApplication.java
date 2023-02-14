package com.CentraleAchat.registeryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegisteryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisteryServiceApplication.class, args);
	}

}
