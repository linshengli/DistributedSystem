package com.morgan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TraderGatewayApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(TraderGatewayApplication.class, args);
	}
}
