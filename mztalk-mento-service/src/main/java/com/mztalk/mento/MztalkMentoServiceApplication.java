package com.mztalk.mento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MztalkMentoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MztalkMentoServiceApplication.class, args);
	}

}
