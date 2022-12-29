package com.mztalk.mentor;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
public class MztalkMentorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MztalkMentorServiceApplication.class, args);
	}


}
