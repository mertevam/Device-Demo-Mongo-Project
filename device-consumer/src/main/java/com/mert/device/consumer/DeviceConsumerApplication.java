package com.mert.device.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
public class DeviceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceConsumerApplication.class, args);

		System.out.println("Device consumer module");
	}

}
