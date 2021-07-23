package com.stackroute.webapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WebapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebapplicationApplication.class, args);
	}

}
