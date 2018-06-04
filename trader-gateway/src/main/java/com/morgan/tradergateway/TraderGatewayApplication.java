package com.morgan.tradergateway;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.morgan.tradergateway.websocket.WSClient;

@SpringBootApplication
public class TraderGatewayApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(TraderGatewayApplication.class, args);
	}
}
