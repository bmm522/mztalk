package com.mztalk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class MztalkGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MztalkGatewayServiceApplication.class, args);
	}

}
