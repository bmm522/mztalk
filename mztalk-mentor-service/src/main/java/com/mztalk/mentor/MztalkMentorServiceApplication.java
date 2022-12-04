package com.mztalk.mentor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MztalkMentorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MztalkMentorServiceApplication.class, args);
	}

}
