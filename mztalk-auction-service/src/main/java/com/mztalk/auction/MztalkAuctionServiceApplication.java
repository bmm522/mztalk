package com.mztalk.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MztalkAuctionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MztalkAuctionServiceApplication.class, args);
	}

}
