package com.mztalk.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MztalkChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MztalkChatServiceApplication.class, args);
	}

}
