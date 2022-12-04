package com.mztalk.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MztalkEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MztalkEurekaServerApplication.class, args);
	}

}
