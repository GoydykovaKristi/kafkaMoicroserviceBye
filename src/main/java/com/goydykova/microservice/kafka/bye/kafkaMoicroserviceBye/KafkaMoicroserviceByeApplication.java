package com.goydykova.microservice.kafka.bye.kafkaMoicroserviceBye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
public class KafkaMoicroserviceByeApplication {


	public static void main(String[] args) {
		SpringApplication.run(KafkaMoicroserviceByeApplication.class, args);
	}

}
