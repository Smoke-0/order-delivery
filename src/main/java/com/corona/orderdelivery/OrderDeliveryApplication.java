package com.corona.orderdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class OrderDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderDeliveryApplication.class, args);
	}

}
